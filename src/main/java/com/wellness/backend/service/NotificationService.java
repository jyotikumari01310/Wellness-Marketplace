package com.wellness.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wellness.backend.entity.Notification;
import com.wellness.backend.entity.User;
import com.wellness.backend.repository.NotificationRepository;
import com.wellness.backend.repository.UserRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepo;
    private final UserRepository userRepo;

    public NotificationService(NotificationRepository notificationRepo,
                               UserRepository userRepo) {
        this.notificationRepo = notificationRepo;
        this.userRepo = userRepo;
    }

    public Notification sendNotification(String email, String message) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setReadStatus(false);
        notification.setCreatedAt(LocalDateTime.now());

        return notificationRepo.save(notification);
    }

    public List<Notification> getMyNotifications(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return notificationRepo.findByUser(user);
    }
}
