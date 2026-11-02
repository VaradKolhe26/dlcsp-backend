package com.example.dlcsp.controller;

import com.example.dlcsp.dto.ApiResponse;
import com.example.dlcsp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public record LoginRequest(String email, String password) {}

    public record RegisterRequest(String name, String email, String phone, String password) {}

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody LoginRequest req, HttpServletRequest request) {
        AuthService.AuthResult r = authService.login(req.email(), req.password(), request);
        return ApiResponse.ok(Map.of("token", r.token(), "role", r.role()), "Login successful");
    }

    @PostMapping("/register")
    public ApiResponse<Map<String, Object>> register(@RequestBody RegisterRequest req, HttpServletRequest request) {
        AuthService.AuthResult r = authService.register(
                req.name(), req.email(), req.phone(), req.password(), request);
        return ApiResponse.ok(Map.of("token", r.token(), "role", r.role()), "Account created");
    }
}