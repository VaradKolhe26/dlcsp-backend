package com.example.dlcsp.model;

public record ScamReport(
        String id,
        String reqId,
        String dateSubmitted,
        String reportedBy,
        String userEmail,
        String userPhone,
        String category,
        String scamType,
        String title,
        String description,
        String priority,
        String status,
        String screenshotUrl) {
}