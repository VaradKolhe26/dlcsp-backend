package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "learning_modules")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class LearningModuleEntity {
    @Id
    private String id;
    @Column(name = "module_number")
    private int number;
    private String title;
    @Column(length = 500)
    private String description;
    private int lessonsCount;
    private int estimatedMinutes;
    private int progressPercent;
    @Column(name = "is_completed")
    private boolean completed;
    private Integer quizScore;
    private String category;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    @Builder.Default
    private List<LessonStepEntity> steps = new ArrayList<>();
}