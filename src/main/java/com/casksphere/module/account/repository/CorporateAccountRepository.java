package com.cashsphere.module.account.repository;

import com.cashsphere.module.account.entity.CorporateAccount;
import com.cashsphere.module.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorporateAccountRepository extends JpaRepository<CorporateAccount, Long> {
    Optional<CorporateAccount> findByAccountNumber(String accountNumber);
    List<CorporateAccount> findByCompany(Company company);
}
