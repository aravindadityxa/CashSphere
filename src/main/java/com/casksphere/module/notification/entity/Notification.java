package com.casksphere.module.notification.entity;

import com.casksphere.module.auth.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_notification_status", columnList = "status"),
        @Index(name = "idx_notification_date", columnList = "created_at")
})
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Column(length = 100)
    private String entityType;

    @Column
    private Long entityId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime readAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = NotificationStatus.UNREAD;
    }

    public enum NotificationType {
        PAYMENT_ALERT, LIQUIDITY_ALERT, LARGE_TRANSACTION, APPROVAL_REQUEST, SYSTEM_ALERT
    }

    public enum NotificationStatus {
        UNREAD, READ, ARCHIVED
    }
}
