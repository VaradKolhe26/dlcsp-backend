package com.example.dlcsp.controller;

import com.example.dlcsp.dto.ApiResponse;
import com.example.dlcsp.model.AuthorizedDevice;
import com.example.dlcsp.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public ApiResponse<List<AuthorizedDevice>> getAll() {
        return ApiResponse.ok(deviceService.getDevicesForCurrentUser(), "Devices fetched");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> remove(@PathVariable String id) {
        return deviceService.removeDevice(id)
                ? ApiResponse.<Void>ok(null, "Device removed")
                : ApiResponse.<Void>error("Device not found");
    }

    @DeleteMapping
    public ApiResponse<Void> signOutOthers() {
        int removed = deviceService.signOutOtherDevices();
        return ApiResponse.<Void>ok(null, removed + " other device(s) signed out");
    }
}