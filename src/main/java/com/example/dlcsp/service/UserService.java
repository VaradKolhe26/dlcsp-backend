package com.example.dlcsp.service;

import com.example.dlcsp.entity.UserEntity;
import com.example.dlcsp.exception.BadRequestException;
import com.example.dlcsp.mapper.EntityMapper;
import com.example.dlcsp.model.UserProfile;
import com.example.dlcsp.repository.UserRepository;
import com.example.dlcsp.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Set<String> USER_STATUSES = Set.of("Active", "Suspended", "Pending Review");

    private final UserRepository users;

    public Optional<UserProfile> getCurrentUser() {
        return users.findById(CurrentUser.id()).map(EntityMapper::toProfile);
    }

    public List<UserProfile> getAllUsers() {
        return users.findAll().stream()
                .map(EntityMapper::toProfile)
                .toList();
    }

    /**
     * Updates only fields the user may change. Null in the request keeps the current value.
     * id, email, role, status, password and progress are never changed here.
     */
    public Optional<UserProfile> updateCurrentUser(UserProfile body) {
        return users.findById(CurrentUser.id()).map(u -> {
            u.setName(pick(body.name(), u.getName()));
            u.setPhone(pick(body.phone(), u.getPhone()));
            u.setLevel(pick(body.level(), u.getLevel()));
            u.setMfaEnabled(pick(body.mfaEnabled(), u.getMfaEnabled()));
            u.setDataSharing(pick(body.dataSharing(), u.getDataSharing()));
            u.setLoginAlerts(pick(body.loginAlerts(), u.getLoginAlerts()));
            u.setBiometricLock(pick(body.biometricLock(), u.getBiometricLock()));
            u.setAutoLockMinutes(pick(body.autoLockMinutes(), u.getAutoLockMinutes()));
            u.setUpiSafetyShield(pick(body.upiSafetyShield(), u.getUpiSafetyShield()));
            u.setSimSwapAlerts(pick(body.simSwapAlerts(), u.getSimSwapAlerts()));
            u.setMaskPersonalDataInReports(pick(body.maskPersonalDataInReports(), u.getMaskPersonalDataInReports()));
            u.setPhishingLinkQuarantine(pick(body.phishingLinkQuarantine(), u.getPhishingLinkQuarantine()));
            u.setUnknownDeviceLockdown(pick(body.unknownDeviceLockdown(), u.getUnknownDeviceLockdown()));
            u.setEmergencyContactPhone(pick(body.emergencyContactPhone(), u.getEmergencyContactPhone()));
            u.setFraudLockdownMode(pick(body.fraudLockdownMode(), u.getFraudLockdownMode()));
            u.setCity(pick(body.city(), u.getCity()));
            u.setState(pick(body.state(), u.getState()));
            u.setOccupation(pick(body.occupation(), u.getOccupation()));
            u.setBio(pick(body.bio(), u.getBio()));
            u.setAvatarUrl(pick(body.avatarUrl(), u.getAvatarUrl()));
            u.setPreferredLanguage(pick(body.preferredLanguage(), u.getPreferredLanguage()));
            u.setPublicBadge(pick(body.publicBadge(), u.getPublicBadge()));
            u.setSmsAlerts(pick(body.smsAlerts(), u.getSmsAlerts()));
            u.setWhatsappDigest(pick(body.whatsappDigest(), u.getWhatsappDigest()));

            return EntityMapper.toProfile(users.save(u));
        });
    }

    /** Admin only: changes a user's status. */
    public Optional<UserProfile> updateUserStatus(String userId, String status) {
        if (status == null || !USER_STATUSES.contains(status)) {
            throw new BadRequestException("status must be one of " + USER_STATUSES);
        }
        return users.findById(userId).map(u -> {
            u.setStatus(status);
            return EntityMapper.toProfile(users.save(u));
        });
    }

    private static <T> T pick(T incoming, T current) {
        return incoming != null ? incoming : current;
    }
}