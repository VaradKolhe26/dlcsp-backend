package com.example.dlcsp.controller;

import com.example.dlcsp.dto.ApiResponse;
import com.example.dlcsp.model.SystemSettingsConfig;
import com.example.dlcsp.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping
    public ApiResponse<SystemSettingsConfig> get() {
        return ApiResponse.ok(settingsService.getSettings(), "Settings fetched");
    }

    @PutMapping
    public ApiResponse<SystemSettingsConfig> update(@RequestBody SystemSettingsConfig body) {
        return ApiResponse.ok(settingsService.updateSettings(body), "Settings updated");
    }
}