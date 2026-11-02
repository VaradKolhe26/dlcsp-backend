package com.example.dlcsp.controller;

import com.example.dlcsp.dto.ApiResponse;
import com.example.dlcsp.model.LearningModule;
import com.example.dlcsp.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modules")
@RequiredArgsConstructor
public class ModuleController {

    public record ProgressRequest(Integer stepIndex, Integer quizScore) {}

    private final ModuleService moduleService;

    @GetMapping
    public ApiResponse<List<LearningModule>> getAll() {
        return ApiResponse.ok(moduleService.getAllModules(), "Modules fetched");
    }

    @GetMapping("/{id}")
    public ApiResponse<LearningModule> getById(@PathVariable String id) {
        return ApiResponse.ok(moduleService.getModuleById(id), "Module fetched");
    }

    @PutMapping("/{id}/progress")
    public ApiResponse<LearningModule> saveProgress(@PathVariable String id, @RequestBody ProgressRequest body) {
        int step = body.stepIndex() == null ? 0 : body.stepIndex();
        return ApiResponse.ok(moduleService.saveProgress(id, step, body.quizScore()), "Progress saved");
    }
}