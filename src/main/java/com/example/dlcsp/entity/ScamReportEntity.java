package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scam_reports")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ScamReportEntity {
    @Id
    private String id;
    private String reqId;
    private String dateSubmitted;
    private String reportedBy;
    private String userEmail;
    private String userPhone;
    private String category;
    private String scamType;
    private String title;
    @Column(length = 2000)
    private String description;
    private String priority;
    private String status;
    @Column(length = 1000)
    private String screenshotUrl;
}