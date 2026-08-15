package com.cashsphere.module.approval.repository;

import com.cashsphere.module.approval.entity.ApprovalRequest;
import com.cashsphere.module.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovalRequestRepository extends JpaRepository<ApprovalRequest, Long> {
    Optional<ApprovalRequest> findByPayment(Payment payment);
    List<ApprovalRequest> findByStatus(ApprovalRequest.ApprovalStatus status);
}
