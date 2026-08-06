package com.casksphere.module.treasury.entity;

import com.casksphere.module.company.entity.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cash_positions", indexes = {
        @Index(name = "idx_company_id", columnList = "company_id"),
        @Index(name = "idx_position_date", columnList = "position_date")
})
public class CashPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private LocalDate positionDate;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalCash;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal availableCash;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal investedCash;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal forecastedCash;

    @Column(length = 10)
    private String currency;

    @Column(length = 500)
    private String notes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (currency == null) currency = "USD";
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
