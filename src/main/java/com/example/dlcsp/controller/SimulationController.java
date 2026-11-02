package com.example.dlcsp.controller;

import com.example.dlcsp.dto.ApiResponse;
import com.example.dlcsp.model.SimulationScenario;
import com.example.dlcsp.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/simulations")
@RequiredArgsConstructor
public class SimulationController {

    private final SimulationService simulationService;

    @GetMapping
    public ApiResponse<List<SimulationScenario>> getAll() {
        return ApiResponse.ok(simulationService.getAllScenarios(), "Simulations fetched");
    }
}