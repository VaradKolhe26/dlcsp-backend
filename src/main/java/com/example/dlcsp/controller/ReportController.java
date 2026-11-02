package com.example.dlcsp.controller;

import com.example.dlcsp.dto.ApiResponse;
import com.example.dlcsp.model.ScamReport;
import com.example.dlcsp.service.ScamReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ScamReportService reportService;

    @GetMapping
    public ApiResponse<List<ScamReport>> getAll() {
        return ApiResponse.ok(reportService.getAllReports(), "Reports fetched");
    }

    @PostMapping
    public ApiResponse<ScamReport> create(@RequestBody ScamReport body) {
        return ApiResponse.ok(reportService.createReport(body), "Report submitted");
    }
}