package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lesson_quizzes")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class LessonQuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "step_id", unique = true)
    private LessonStepEntity step;

    @Column(length = 1000)
    private String question;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "quiz_options", joinColumns = @JoinColumn(name = "quiz_id"))
    @OrderColumn(name = "option_index")
    @Column(length = 500)
    private List<String> options;

    private int correctIndex;

    @Column(length = 1000)
    private String explanation;
}