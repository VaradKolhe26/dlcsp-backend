package com.example.dlcsp.model;

public record SystemSettingsConfig(
        String platformName,
        String adminEmail,
        String timezone,
        boolean dailySummaryEmails,
        boolean criticalSecurityAlerts,
        int minPasswordLength,
        String authRequirement,
        int sessionTimeoutMinutes) {
}