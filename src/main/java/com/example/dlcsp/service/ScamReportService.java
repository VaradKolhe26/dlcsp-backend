package com.example.dlcsp.service;

import com.example.dlcsp.entity.ScamReportEntity;
import com.example.dlcsp.exception.BadRequestException;
import com.example.dlcsp.mapper.EntityMapper;
import com.example.dlcsp.model.ScamReport;
import com.example.dlcsp.repository.ScamReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.dlcsp.exception.ResourceNotFoundException;

import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScamReportService {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");

    private final ScamReportRepository reports;

    public List<ScamReport> getAllReports() {
        return reports.findAll().stream()
                .map(EntityMapper::toReport)
                .toList();
    }

    public ScamReport createReport(ScamReport body) {
        validate(body);

        ScamReportEntity saved = reports.save(ScamReportEntity.builder()
                .id("rep-" + UUID.randomUUID().toString().substring(0, 8))
                .reqId("#REQ-" + (8901 + reports.count()))
                .dateSubmitted(LocalDateTime.now().format(DATE_FORMAT))
                .reportedBy(body.reportedBy())
                .userEmail(body.userEmail())
                .userPhone(body.userPhone())
                .category(body.category())
                .scamType(body.scamType())
                .title(body.title())
                .description(body.description())
                .priority(body.priority())
                .status("Pending")
                .screenshotUrl(body.screenshotUrl())
                .build());

        return EntityMapper.toReport(saved);
    }

    private void validate(ScamReport body) {
        if (body == null) {
            throw new BadRequestException("Report body is required");
        }
        if (isBlank(body.title())) {
            throw new BadRequestException("title is required");
        }
        if (isBlank(body.category())) {
            throw new BadRequestException("category is required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
    private static final Set<String> REPORT_STATUSES = Set.of("Pending", "Approved", "Rejected", "Resolved");

    public ScamReport updateStatus(String id, String status) {
        if (status == null || !REPORT_STATUSES.contains(status)) {
            throw new BadRequestException("status must be one of " + REPORT_STATUSES);
        }
        ScamReportEntity e = reports.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found: " + id));
        e.setStatus(status);
        return EntityMapper.toReport(reports.save(e));
    }
}