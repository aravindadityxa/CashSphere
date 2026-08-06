package com.casksphere.module.treasury.repository;

import com.casksphere.module.treasury.entity.CashPosition;
import com.casksphere.module.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CashPositionRepository extends JpaRepository<CashPosition, Long> {
    Optional<CashPosition> findByCompanyAndPositionDate(Company company, LocalDate positionDate);
    List<CashPosition> findByCompanyOrderByPositionDateDesc(Company company);
}
