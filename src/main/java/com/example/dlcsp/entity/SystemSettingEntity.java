package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "system_settings")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class SystemSettingEntity {
    @Id
    private Long id;
    private String platformName;
    private String adminEmail;
    private String timezone;
    private boolean dailySummaryEmails;
    private boolean criticalSecurityAlerts;
    private int minPasswordLength;
    private String authRequirement;
    private int sessionTimeoutMinutes;
}