package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authorized_devices")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AuthorizedDeviceEntity {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String deviceName;
    private String location;
    @Column(name = "is_current")
    private boolean current;
    private String lastActive;
    private String browser;
}