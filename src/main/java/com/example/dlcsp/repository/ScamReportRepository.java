package com.example.dlcsp.repository;

import com.example.dlcsp.entity.ScamReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScamReportRepository extends JpaRepository<ScamReportEntity, String> {
}