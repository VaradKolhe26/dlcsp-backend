package com.example.dlcsp.service;

import com.example.dlcsp.entity.SystemSettingEntity;
import com.example.dlcsp.exception.ResourceNotFoundException;
import com.example.dlcsp.mapper.EntityMapper;
import com.example.dlcsp.model.SystemSettingsConfig;
import com.example.dlcsp.repository.SystemSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingsService {

    private static final long SETTINGS_ID = 1L;

    private final SystemSettingRepository settings;

    public SystemSettingsConfig getSettings() {
        return settings.findById(SETTINGS_ID)
                .map(EntityMapper::toSettings)
                .orElseThrow(() -> new ResourceNotFoundException("Settings not found"));
    }

    public SystemSettingsConfig updateSettings(SystemSettingsConfig body) {
        if (body == null) {
            throw new IllegalArgumentException("Settings body is required");
        }

        SystemSettingEntity s = settings.findById(SETTINGS_ID)
                .orElseThrow(() -> new ResourceNotFoundException("Settings not found"));

        s.setPlatformName(body.platformName());
        s.setAdminEmail(body.adminEmail());
        s.setTimezone(body.timezone());
        s.setDailySummaryEmails(body.dailySummaryEmails());
        s.setCriticalSecurityAlerts(body.criticalSecurityAlerts());
        s.setMinPasswordLength(body.minPasswordLength());
        s.setAuthRequirement(body.authRequirement());
        s.setSessionTimeoutMinutes(body.sessionTimeoutMinutes());

        return EntityMapper.toSettings(settings.save(s));
    }
}