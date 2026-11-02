package com.example.dlcsp.model;

public record AuthorizedDevice(
        String id,
        String deviceName,
        String location,
        boolean isCurrent,
        String lastActive,
        String browser) {
}