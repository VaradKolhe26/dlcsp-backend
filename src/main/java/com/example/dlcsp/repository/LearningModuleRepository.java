package com.example.dlcsp.repository;

import com.example.dlcsp.entity.LearningModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningModuleRepository extends JpaRepository<LearningModuleEntity, String> {
    List<LearningModuleEntity> findAllByOrderByNumberAsc();
}