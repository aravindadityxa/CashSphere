package com.casksphere.module.payment.repository;

import com.casksphere.module.payment.entity.Beneficiary;
import com.casksphere.module.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    Optional<Beneficiary> findByAccountNumber(String accountNumber);
    List<Beneficiary> findByCompany(Company company);
}
