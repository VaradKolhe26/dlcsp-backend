package com.example.dlcsp.repository;

import com.example.dlcsp.entity.UserModuleProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserModuleProgressRepository extends JpaRepository<UserModuleProgressEntity, String> {
    List<UserModuleProgressEntity> findByUserId(String userId);
}