package com.example.dlcsp.service;

import com.example.dlcsp.entity.AuthorizedDeviceEntity;
import com.example.dlcsp.mapper.EntityMapper;
import com.example.dlcsp.model.AuthorizedDevice;
import com.example.dlcsp.repository.AuthorizedDeviceRepository;
import com.example.dlcsp.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final AuthorizedDeviceRepository devices;

    @Transactional(readOnly = true)
    public List<AuthorizedDevice> getDevicesForCurrentUser() {
        return devices.findByUserId(CurrentUser.id()).stream()
                .map(EntityMapper::toDevice)
                .toList();
    }

    /** Removes one device, only if it belongs to the current user. */
    @Transactional
    public boolean removeDevice(String deviceId) {
        String uid = CurrentUser.id();
        return devices.findById(deviceId)
                .filter(d -> d.getUser() != null && uid.equals(d.getUser().getId()))
                .map(d -> {
                    devices.delete(d);
                    return true;
                })
                .orElse(false);
    }

    /** Removes every device except the one marked current. Returns how many were removed. */
    @Transactional
    public int signOutOtherDevices() {
        String uid = CurrentUser.id();
        List<AuthorizedDeviceEntity> others = devices.findByUserId(uid).stream()
                .filter(d -> !d.isCurrent())
                .toList();
        devices.deleteAll(others);
        return others.size();
    }
}