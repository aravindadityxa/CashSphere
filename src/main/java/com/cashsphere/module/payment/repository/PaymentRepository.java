package com.cashsphere.module.payment.repository;

import com.cashsphere.module.payment.entity.Payment;
import com.cashsphere.module.account.entity.CorporateAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByReferenceNumber(String referenceNumber);
    List<Payment> findByFromAccount(CorporateAccount fromAccount);
}
