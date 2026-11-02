package com.example.dlcsp.repository;

import com.example.dlcsp.entity.MentorMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorMessageRepository extends JpaRepository<MentorMessageEntity, String> {
    List<MentorMessageEntity> findAllByOrderByPositionAsc();
}