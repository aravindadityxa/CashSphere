package com.cashsphere.module.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies", indexes = {
        @Index(name = "idx_company_code", columnList = "company_code"),
        @Index(name = "idx_company_status", columnList = "status")
})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String companyCode;

    @Column(nullable = false, length = 100)
    private String companyName;

    @Column(length = 500)
    private String description;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = CompanyStatus.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum CompanyStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }
}
