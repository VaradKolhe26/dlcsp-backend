package com.example.dlcsp.controller;

import com.example.dlcsp.dto.ApiResponse;
import com.example.dlcsp.model.UserProfile;
import com.example.dlcsp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/me")
    public ApiResponse<UserProfile> currentUser() {
        return userService.getCurrentUser()
                .map(u -> ApiResponse.ok(u, "Current user fetched"))
                .orElseGet(() -> ApiResponse.error("User not found"));
    }

    @PutMapping("/users/me")
    public ApiResponse<UserProfile> updateCurrentUser(@RequestBody UserProfile body) {
        return userService.updateCurrentUser(body)
                .map(u -> ApiResponse.ok(u, "Profile updated"))
                .orElseGet(() -> ApiResponse.error("User not found"));
    }

    @GetMapping("/admin/users/me")
    public ApiResponse<UserProfile> adminUser() {
        return userService.getCurrentUser()
                .map(u -> ApiResponse.ok(u, "Admin fetched"))
                .orElseGet(() -> ApiResponse.error("Admin not found"));
    }

    @GetMapping("/admin/users")
    public ApiResponse<List<UserProfile>> allUsers() {
        return ApiResponse.ok(userService.getAllUsers(), "Users fetched");
    }

    @PutMapping("/admin/users/{id}/status")
    public ApiResponse<UserProfile> setUserStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        return userService.updateUserStatus(id, body.get("status"))
                .map(u -> ApiResponse.ok(u, "User status updated"))
                .orElseGet(() -> ApiResponse.error("User not found"));
    }
}