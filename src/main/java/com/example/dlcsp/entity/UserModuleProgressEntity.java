package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_module_progress")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class UserModuleProgressEntity {
    // Composite key as a string: "<userId>|<moduleId>"
    @Id
    private String id;
    private String userId;
    private String moduleId;
    private int stepIndex;
    private Integer quizScore;
}