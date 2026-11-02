package com.example.dlcsp.model;

import java.util.List;

public record LearningModule(
        String id,
        int number,
        String title,
        String description,
        int lessonsCount,
        int estimatedMinutes,
        int progressPercent,
        boolean isCompleted,
        Integer quizScore,
        String category,
        List<LessonStep> steps) {
}