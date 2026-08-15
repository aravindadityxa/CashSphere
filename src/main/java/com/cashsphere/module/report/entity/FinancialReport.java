package com.cashsphere.module.report.entity;

import com.cashsphere.module.company.entity.Company;
import com.cashsphere.module.auth.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "financial_reports", indexes = {
        @Index(name = "idx_company_id", columnList = "company_id"),
        @Index(name = "idx_report_type", columnList = "report_type"),
        @Index(name = "idx_report_date", columnList = "report_date")
})
public class FinancialReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false, length = 100)
    private String reportName;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Column(nullable = false)
    private LocalDate reportDate;

    @Column(length = 500)
    private String reportPath;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ReportFormat reportFormat;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generated_by")
    private User generatedBy;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = ReportStatus.GENERATED;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum ReportType {
        CASH_FLOW, LIQUIDITY, PAYMENTS, COMPANY_OVERVIEW, ANALYTICS
    }

    public enum ReportFormat {
        PDF, EXCEL, CSV
    }

    public enum ReportStatus {
        DRAFT, GENERATED, SENT, ARCHIVED
    }
}
