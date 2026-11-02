package com.example.dlcsp.service;

import com.example.dlcsp.entity.LearningModuleEntity;
import com.example.dlcsp.entity.UserModuleProgressEntity;
import com.example.dlcsp.exception.BadRequestException;
import com.example.dlcsp.exception.ResourceNotFoundException;
import com.example.dlcsp.mapper.EntityMapper;
import com.example.dlcsp.model.LearningModule;
import com.example.dlcsp.repository.LearningModuleRepository;
import com.example.dlcsp.repository.UserModuleProgressRepository;
import com.example.dlcsp.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModuleService {

    private final LearningModuleRepository modules;
    private final UserModuleProgressRepository progress;

    public List<LearningModule> getAllModules() {
        Map<String, UserModuleProgressEntity> mine = myProgress();
        return modules.findAllByOrderByNumberAsc().stream()
                .map(m -> withProgress(EntityMapper.toModule(m), mine.get(m.getId())))
                .toList();
    }

    public LearningModule getModuleById(String id) {
        LearningModuleEntity m = modules.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found: " + id));
        String uid = currentUserIdOrNull();
        UserModuleProgressEntity p = uid == null ? null : progress.findById(uid + "|" + id).orElse(null);
        return withProgress(EntityMapper.toModule(m), p);
    }

    /** Saves the furthest step reached by the current user. Progress never moves backwards. */
    @Transactional
    public LearningModule saveProgress(String moduleId, int stepIndex, Integer quizScore) {
        LearningModuleEntity m = modules.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found: " + moduleId));

        int total = m.getSteps().size();
        if (stepIndex < 0 || (total > 0 && stepIndex >= total)) {
            throw new BadRequestException("stepIndex must be between 0 and " + (total - 1));
        }

        String uid = CurrentUser.id();
        String k = uid + "|" + moduleId;
        UserModuleProgressEntity p = progress.findById(k).orElseGet(() -> {
            UserModuleProgressEntity n = new UserModuleProgressEntity();
            n.setId(k);
            n.setUserId(uid);
            n.setModuleId(moduleId);
            return n;
        });

        p.setStepIndex(Math.max(p.getStepIndex(), stepIndex));
        if (quizScore != null) {
            p.setQuizScore(quizScore);
        }
        progress.save(p);

        return withProgress(EntityMapper.toModule(m), p);
    }

    /** Overlays the current user's progress. Without a record, progress is zero, never the stored row's values. */
    private LearningModule withProgress(LearningModule base, UserModuleProgressEntity p) {
        int total = base.steps().size();
        int percent = 0;
        Integer quiz = null;

        if (p != null) {
            percent = total == 0 ? 0 : Math.min(100, (int) Math.round((p.getStepIndex() + 1) * 100.0 / total));
            quiz = p.getQuizScore();
        }

        return new LearningModule(
                base.id(), base.number(), base.title(), base.description(),
                base.lessonsCount(), base.estimatedMinutes(),
                percent, percent == 100,
                quiz,
                base.category(), base.steps());
    }

    /** Returns the current user's progress keyed by module, or empty for public (not logged-in) requests. */
    private Map<String, UserModuleProgressEntity> myProgress() {
        String uid = currentUserIdOrNull();
        if (uid == null) {
            return Map.of();
        }
        return progress.findByUserId(uid).stream()
                .collect(Collectors.toMap(UserModuleProgressEntity::getModuleId, p -> p));
    }

    private String currentUserIdOrNull() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken || auth.getPrincipal() == null) {
            return null;
        }
        return auth.getPrincipal().toString();
    }
}