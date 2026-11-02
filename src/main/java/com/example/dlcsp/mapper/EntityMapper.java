package com.example.dlcsp.mapper;

import com.example.dlcsp.entity.*;
import com.example.dlcsp.model.*;

import java.util.List;

public final class EntityMapper {

    private EntityMapper() {}

    public static UserProfile toProfile(UserEntity u) {
        return new UserProfile(u.getId(), u.getName(), u.getPhone(), u.getEmail(),
                u.getRole(), u.getLevel(), u.getSecurityScore(), u.getStatus(), u.getLastLogin(),
                u.getCompletedModules(), u.getTotalModules(), u.getMfaEnabled(), u.getDataSharing(),
                u.getLoginAlerts(), u.getBiometricLock(), u.getAutoLockMinutes(), u.getUpiSafetyShield(),
                u.getSimSwapAlerts(), u.getMaskPersonalDataInReports(), u.getPhishingLinkQuarantine(),
                u.getUnknownDeviceLockdown(), u.getEmergencyContactPhone(), u.getFraudLockdownMode(),
                u.getCity(), u.getState(), u.getOccupation(), u.getBio(), u.getAvatarUrl(),
                u.getPreferredLanguage(), u.getPublicBadge(), u.getSmsAlerts(), u.getWhatsappDigest());
    }

    public static LearningModule toModule(LearningModuleEntity m) {
        List<LessonStep> steps = m.getSteps().stream().map(EntityMapper::toStep).toList();
        return new LearningModule(m.getId(), m.getNumber(), m.getTitle(), m.getDescription(),
                m.getLessonsCount(), m.getEstimatedMinutes(), m.getProgressPercent(),
                m.isCompleted(), m.getQuizScore(), m.getCategory(), steps);
    }

    public static LessonStep toStep(LessonStepEntity s) {
        LessonQuiz quiz = null;
        if (s.getQuiz() != null) {
            LessonQuizEntity q = s.getQuiz();
            quiz = new LessonQuiz(q.getQuestion(), q.getOptions(), q.getCorrectIndex(), q.getExplanation());
        }
        return new LessonStep(publicStepId(s), s.getTitle(), s.getSubtitle(), s.getContent(),
                s.getTip(), s.getWarning(), quiz);
    }

    /** Converts the database step ID "mod-1-s1" back to the frontend ID "s1". */
    private static String publicStepId(LessonStepEntity s) {
        String dbId = s.getId();
        String prefix = s.getModule().getId() + "-";
        return dbId.startsWith(prefix) ? dbId.substring(prefix.length()) : dbId;
    }

    public static ScamReport toReport(ScamReportEntity r) {
        return new ScamReport(r.getId(), r.getReqId(), r.getDateSubmitted(), r.getReportedBy(),
                r.getUserEmail(), r.getUserPhone(), r.getCategory(), r.getScamType(), r.getTitle(),
                r.getDescription(), r.getPriority(), r.getStatus(), r.getScreenshotUrl());
    }

    public static SimulationScenario toScenario(SimulationScenarioEntity s) {
        return new SimulationScenario(s.getId(), s.getType(), s.getTitle(), s.getDifficulty(),
                s.getScenarioDescription(), s.getMockSender(), s.getMockContent(),
                s.getFakeLinkOrTarget(), s.getRedFlags(), s.getExplanation(), s.getCorrectAction());
    }

    public static AuthorizedDevice toDevice(AuthorizedDeviceEntity d) {
        return new AuthorizedDevice(d.getId(), d.getDeviceName(), d.getLocation(),
                d.isCurrent(), d.getLastActive(), d.getBrowser());
    }

    public static SystemSettingsConfig toSettings(SystemSettingEntity s) {
        return new SystemSettingsConfig(s.getPlatformName(), s.getAdminEmail(), s.getTimezone(),
                s.isDailySummaryEmails(), s.isCriticalSecurityAlerts(), s.getMinPasswordLength(),
                s.getAuthRequirement(), s.getSessionTimeoutMinutes());
    }

    public static MentorChatMessage toMessage(MentorMessageEntity m) {
        return new MentorChatMessage(m.getId(), m.getSender(), m.getMessageText(),
                m.getSentTime(), m.getOptions());
    }
}