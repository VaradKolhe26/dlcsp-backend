package com.example.dlcsp.model;

import java.util.List;

public record MentorChatMessage(
        String id,
        String sender,
        String text,
        String timestamp,
        List<String> options) {
}