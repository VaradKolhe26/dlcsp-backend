package com.example.dlcsp.service;

import com.example.dlcsp.entity.AuthorizedDeviceEntity;
import com.example.dlcsp.entity.UserEntity;
import com.example.dlcsp.exception.BadRequestException;
import com.example.dlcsp.repository.AuthorizedDeviceRepository;
import com.example.dlcsp.repository.UserRepository;
import com.example.dlcsp.security.JwtService;
import com.example.dlcsp.util.IdGenerator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    public record AuthResult(String token, String role) {}

    private final UserRepository users;
    private final AuthorizedDeviceRepository devices;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResult login(String email, String password, HttpServletRequest request) {
        if (email == null || password == null || email.isBlank() || password.isBlank()) {
            throw new BadRequestException("Email and password are required");
        }
        UserEntity user = users.findByEmail(email.trim().toLowerCase())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BadRequestException("Invalid email or password");
        }
        if ("Suspended".equalsIgnoreCase(user.getStatus())) {
            throw new BadRequestException("This account is suspended. Please contact support.");
        }

        recordDevice(user, request);
        return new AuthResult(jwtService.generateToken(user.getId(), user.getRole()), user.getRole());
    }

    public AuthResult register(String name, String email, String phone, String password, HttpServletRequest request) {
        if (name == null || name.isBlank()) throw new BadRequestException("Full name is required");
        if (email == null || !email.contains("@")) throw new BadRequestException("A valid email is required");
        if (phone == null || phone.isBlank()) throw new BadRequestException("Phone number is required");
        if (password == null || password.length() < 6) {
            throw new BadRequestException("Password must be at least 6 characters");
        }

        String cleanEmail = email.trim().toLowerCase();
        if (users.findByEmail(cleanEmail).isPresent()) {
            throw new BadRequestException("An account with this email already exists");
        }

        UserEntity u = new UserEntity();
        u.setId(IdGenerator.newUserId());
        u.setName(name.trim());
        u.setEmail(cleanEmail);
        u.setPhone(phone.trim());
        u.setPasswordHash(passwordEncoder.encode(password));
        u.setRole("USER");
        u.setLevel("Level 1 Beginner");
        u.setSecurityScore(0);
        u.setStatus("Active");
        u.setLastLogin("Just now");
        u.setCompletedModules(0);
        u.setTotalModules(5);
        u.setMfaEnabled(false);
        u.setDataSharing(true);
        u.setLoginAlerts(true);
        u.setBiometricLock(false);
        u.setAutoLockMinutes(15);
        u.setUpiSafetyShield(false);
        u.setSimSwapAlerts(false);
        u.setMaskPersonalDataInReports(false);
        u.setPhishingLinkQuarantine(false);
        u.setUnknownDeviceLockdown(false);
        u.setFraudLockdownMode(false);
        u.setPreferredLanguage("en");
        u.setPublicBadge(true);
        u.setSmsAlerts(true);
        u.setWhatsappDigest(true);
        users.save(u);

        recordDevice(u, request);
        return new AuthResult(jwtService.generateToken(u.getId(), u.getRole()), u.getRole());
    }

    /** Marks the previous sessions as not current and stores this login as the current device. */
    private void recordDevice(UserEntity user, HttpServletRequest request) {
        String ua = request.getHeader("User-Agent") == null ? "" : request.getHeader("User-Agent");

        List<AuthorizedDeviceEntity> existing = devices.findByUserId(user.getId());
        existing.forEach(d -> d.setCurrent(false));
        devices.saveAll(existing);

        AuthorizedDeviceEntity d = new AuthorizedDeviceEntity();
        d.setId("DEV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        d.setUser(user);
        d.setDeviceName(detectDevice(ua));
        d.setBrowser(detectBrowser(ua));
        d.setLocation("Network " + request.getRemoteAddr());
        d.setCurrent(true);
        d.setLastActive("Just now");
        devices.save(d);
    }

    private String detectDevice(String ua) {
        if (ua.contains("iPhone")) return "iPhone";
        if (ua.contains("Android")) return "Android device";
        if (ua.contains("iPad")) return "iPad";
        if (ua.contains("Windows")) return "Windows PC";
        if (ua.contains("Macintosh")) return "Mac";
        if (ua.contains("Linux")) return "Linux PC";
        return "Unknown device";
    }

    private String detectBrowser(String ua) {
        if (ua.contains("Edg/")) return "Microsoft Edge";
        if (ua.contains("Chrome/")) return "Google Chrome";
        if (ua.contains("Firefox/")) return "Firefox";
        if (ua.contains("Safari/")) return "Safari";
        return "Unknown browser";
    }
}