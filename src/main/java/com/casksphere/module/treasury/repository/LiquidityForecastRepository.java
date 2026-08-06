package com.casksphere.module.treasury.repository;

import com.casksphere.module.treasury.entity.LiquidityForecast;
import com.casksphere.module.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LiquidityForecastRepository extends JpaRepository<LiquidityForecast, Long> {
    Optional<LiquidityForecast> findByCompanyAndForecastDate(Company company, LocalDate forecastDate);
    List<LiquidityForecast> findByCompanyOrderByForecastDateDesc(Company company);
}
