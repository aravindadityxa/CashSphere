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
@Table(name = "liquidity_forecasts", indexes = {
        @Index(name = "idx_company_id", columnList = "company_id"),
        @Index(name = "idx_forecast_date", columnList = "forecast_date")
})
public class LiquidityForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private LocalDate forecastDate;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal projectedInflows;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal projectedOutflows;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal netProjection;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal confidence;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ForecastStatus status;

    @Column(length = 500)
    private String assumptions;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = ForecastStatus.DRAFT;
        if (confidence == null) confidence = BigDecimal.valueOf(80);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum ForecastStatus {
        DRAFT, PUBLISHED, ARCHIVED
    }
}
