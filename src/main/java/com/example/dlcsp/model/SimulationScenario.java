package com.example.dlcsp.model;

import java.util.List;

public record SimulationScenario(
        String id,
        String type,
        String title,
        String difficulty,
        String scenarioDescription,
        String mockSender,
        String mockContent,
        String fakeLinkOrTarget,
        List<String> redFlags,
        String explanation,
        String correctAction) {
}