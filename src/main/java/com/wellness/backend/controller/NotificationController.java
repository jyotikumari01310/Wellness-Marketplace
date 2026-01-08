package com.wellness.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.Notification;
import com.wellness.backend.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public Notification send(
            @RequestParam String email,
            @RequestParam String message) {

        return notificationService.sendNotification(email, message);
    }

    @GetMapping
    public List<Notification> myNotifications(@RequestParam String email) {
        return notificationService.getMyNotifications(email);
    }
}
