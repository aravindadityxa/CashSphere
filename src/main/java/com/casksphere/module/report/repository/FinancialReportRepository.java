package com.casksphere.module.report.repository;

import com.casksphere.module.report.entity.FinancialReport;
import com.casksphere.module.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialReportRepository extends JpaRepository<FinancialReport, Long> {
    List<FinancialReport> findByCompanyOrderByReportDateDesc(Company company);
    List<FinancialReport> findByCompanyAndReportTypeOrderByReportDateDesc(Company company, FinancialReport.ReportType reportType);
}
