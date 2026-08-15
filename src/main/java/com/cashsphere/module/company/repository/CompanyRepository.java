package com.cashsphere.module.company.repository;

import com.cashsphere.module.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyCode(String companyCode);
    Optional<Company> findByCompanyName(String companyName);
}
