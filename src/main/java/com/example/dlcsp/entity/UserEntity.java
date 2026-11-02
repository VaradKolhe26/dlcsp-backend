package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String phone;
    private String email;
    private String passwordHash;
    private String role;
    private String level;
    private int securityScore;
    private String status;
    private String lastLogin;
    private Integer completedModules;
    private Integer totalModules;
    private Boolean mfaEnabled;
    private Boolean dataSharing;
    private Boolean loginAlerts;
    private Boolean biometricLock;
    private Integer autoLockMinutes;
    private Boolean upiSafetyShield;
    private Boolean simSwapAlerts;
    private Boolean maskPersonalDataInReports;
    private Boolean phishingLinkQuarantine;
    private Boolean unknownDeviceLockdown;
    private String emergencyContactPhone;
    private Boolean fraudLockdownMode;
    private String city;
    @Column(name = "state_name")
    private String state;
    private String occupation;
    @Column(length = 1000)
    private String bio;
    @Column(length = 1000)
    private String avatarUrl;

    // Preferences (new in Phase 2)
    private String preferredLanguage;
    private Boolean publicBadge;
    private Boolean smsAlerts;
    private Boolean whatsappDigest;
}