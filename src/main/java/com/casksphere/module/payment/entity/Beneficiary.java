package com.casksphere.module.payment.entity;

import com.casksphere.module.company.entity.Company;
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
@Table(name = "beneficiaries", indexes = {
        @Index(name = "idx_beneficiary_account", columnList = "account_number"),
        @Index(name = "idx_company_id", columnList = "company_id"),
        @Index(name = "idx_beneficiary_status", columnList = "status")
})
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false, length = 100)
    private String beneficiaryName;

    @Column(nullable = false, length = 20)
    private String accountNumber;

    @Column(length = 10)
    private String routingNumber;

    @Column(nullable = false, length = 50)
    private String bankName;

    @Column(length = 50)
    private String bankCode;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private BeneficiaryStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = BeneficiaryStatus.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum BeneficiaryStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }
}
