package com.example.dlcsp.model;

import java.util.List;

public record LessonStep(
        String id,
        String title,
        String subtitle,
        String content,
        String tip,
        String warning,
        LessonQuiz quiz) {
}