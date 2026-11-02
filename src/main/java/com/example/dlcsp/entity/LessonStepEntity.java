package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lesson_steps")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class LessonStepEntity {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private LearningModuleEntity module;
    @Column(name = "step_position")
    private int position;
    private String title;
    private String subtitle;
    @Column(length = 2000)
    private String content;
    @Column(length = 1000)
    private String tip;
    @Column(length = 1000)
    private String warning;

    @OneToOne(mappedBy = "step", cascade = CascadeType.ALL, orphanRemoval = true)
    private LessonQuizEntity quiz;
}