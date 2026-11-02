package com.example.dlcsp.controller;

import com.example.dlcsp.dto.ApiResponse;
import com.example.dlcsp.model.ScamReport;
import com.example.dlcsp.service.ScamReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/reports")
@RequiredArgsConstructor
public class AdminReportController {

    private final ScamReportService reportService;

    @PutMapping("/{id}/status")
    public ApiResponse<ScamReport> updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        return ApiResponse.ok(reportService.updateStatus(id, body.get("status")), "Report status updated");
    }
}