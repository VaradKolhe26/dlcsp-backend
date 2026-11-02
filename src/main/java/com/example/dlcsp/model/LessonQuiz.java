package com.example.dlcsp.model;

import java.util.List;

public record LessonQuiz(
        String question,
        List<String> options,
        int correctIndex,
        String explanation) {
}