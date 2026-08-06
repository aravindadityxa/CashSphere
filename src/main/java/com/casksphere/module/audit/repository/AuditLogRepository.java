package com.cashsphere.module.audit.repository;

import com.cashsphere.module.audit.entity.AuditLog;
import com.cashsphere.module.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUserOrderByCreatedAtDesc(User user);
    List<AuditLog> findByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime startDate, LocalDateTime endDate);
}
