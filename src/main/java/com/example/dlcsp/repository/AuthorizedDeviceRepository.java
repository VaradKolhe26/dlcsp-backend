package com.example.dlcsp.repository;

import com.example.dlcsp.entity.AuthorizedDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorizedDeviceRepository extends JpaRepository<AuthorizedDeviceEntity, String> {
    List<AuthorizedDeviceEntity> findByUserId(String userId);
}