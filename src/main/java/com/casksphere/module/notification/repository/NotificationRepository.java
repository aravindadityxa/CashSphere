package com.cashsphere.module.notification.repository;

import com.cashsphere.module.notification.entity.Notification;
import com.cashsphere.module.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndStatusOrderByCreatedAtDesc(User user, Notification.NotificationStatus status);
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
}
