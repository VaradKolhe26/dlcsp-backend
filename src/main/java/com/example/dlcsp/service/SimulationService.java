package com.example.dlcsp.service;

import com.example.dlcsp.mapper.EntityMapper;
import com.example.dlcsp.model.SimulationScenario;
import com.example.dlcsp.repository.SimulationScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final SimulationScenarioRepository scenarios;

    public List<SimulationScenario> getAllScenarios() {
        return scenarios.findAll().stream()
                .map(EntityMapper::toScenario)
                .toList();
    }
}