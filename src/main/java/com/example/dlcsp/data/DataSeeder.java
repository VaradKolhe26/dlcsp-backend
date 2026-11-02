package com.example.dlcsp.data;

import com.example.dlcsp.entity.*;
import com.example.dlcsp.model.*;
import com.example.dlcsp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository users;
    private final LearningModuleRepository modules;
    private final SimulationScenarioRepository scenarios;
    private final SystemSettingRepository settings;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.bootstrap.admin-email:}")
    private String adminEmail;

    @Value("${app.bootstrap.admin-password:}")
    private String adminPassword;

    @Override
    @Transactional
    public void run(String... args) {
        if (modules.count() == 0) seedModules();
        if (scenarios.count() == 0) seedSimulations();
        if (!settings.existsById(1L)) seedSettings();
        if (users.count() == 0) createFirstAdmin();
    }

    // Creates one admin from environment variables. Nothing is created if they are not set.
    private void createFirstAdmin() {
        if (adminEmail == null || adminEmail.isBlank()
                || adminPassword == null || adminPassword.isBlank()) {
            return;
        }
        UserEntity admin = new UserEntity();
        admin.setId("ADM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        admin.setName("Administrator");
        admin.setEmail(adminEmail.trim().toLowerCase());
        admin.setPasswordHash(passwordEncoder.encode(adminPassword));
        admin.setRole("ADMIN");
        admin.setLevel("System Administrator");
        admin.setStatus("Active");
        admin.setSecurityScore(0);
        admin.setMfaEnabled(false);
        admin.setDataSharing(false);
        admin.setLoginAlerts(true);
        users.save(admin);
    }

    private void seedModules() {
        for (LearningModule m : MockData.MODULES) {
            LearningModuleEntity me = new LearningModuleEntity();
            me.setId(m.id());
            me.setNumber(m.number());
            me.setTitle(m.title());
            me.setDescription(m.description());
            me.setLessonsCount(m.lessonsCount());
            me.setEstimatedMinutes(m.estimatedMinutes());
            me.setProgressPercent(0);
            me.setCompleted(false);
            me.setQuizScore(null);
            me.setCategory(m.category());

            for (int i = 0; i < m.steps().size(); i++) {
                LessonStep s = m.steps().get(i);

                LessonStepEntity se = new LessonStepEntity();
                se.setId(m.id() + "-" + s.id());
                se.setModule(me);
                se.setPosition(i);
                se.setTitle(s.title());
                se.setSubtitle(s.subtitle());
                se.setContent(s.content());
                se.setTip(s.tip());
                se.setWarning(s.warning());

                if (s.quiz() != null) {
                    LessonQuiz q = s.quiz();
                    LessonQuizEntity qe = new LessonQuizEntity();
                    qe.setStep(se);
                    qe.setQuestion(q.question());
                    qe.setOptions(new ArrayList<>(q.options()));
                    qe.setCorrectIndex(q.correctIndex());
                    qe.setExplanation(q.explanation());
                    se.setQuiz(qe);
                }

                me.getSteps().add(se);
            }
            modules.save(me);
        }
    }

    private void seedSimulations() {
        MockData.SIMULATIONS.forEach(s -> {
            SimulationScenarioEntity e = new SimulationScenarioEntity();
            e.setId(s.id());
            e.setType(s.type());
            e.setTitle(s.title());
            e.setDifficulty(s.difficulty());
            e.setScenarioDescription(s.scenarioDescription());
            e.setMockSender(s.mockSender());
            e.setMockContent(s.mockContent());
            e.setFakeLinkOrTarget(s.fakeLinkOrTarget());
            e.setRedFlags(new ArrayList<>(s.redFlags()));
            e.setExplanation(s.explanation());
            e.setCorrectAction(s.correctAction());
            scenarios.save(e);
        });
    }

    private void seedSettings() {
        SystemSettingsConfig cfg = MockData.SETTINGS;
        SystemSettingEntity e = new SystemSettingEntity();
        e.setId(1L);
        e.setPlatformName(cfg.platformName());
        e.setAdminEmail(cfg.adminEmail());
        e.setTimezone(cfg.timezone());
        e.setDailySummaryEmails(cfg.dailySummaryEmails());
        e.setCriticalSecurityAlerts(cfg.criticalSecurityAlerts());
        e.setMinPasswordLength(cfg.minPasswordLength());
        e.setAuthRequirement(cfg.authRequirement());
        e.setSessionTimeoutMinutes(cfg.sessionTimeoutMinutes());
        settings.save(e);
    }
}