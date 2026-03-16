package com.hps.orchestraspa.controller;

import com.hps.orchestraspa.model.Notification;
import com.hps.orchestraspa.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/all")
    public ResponseEntity<Notification[]> getAll() throws Exception {
        return ResponseEntity.ok(notificationService.getAll());
    }
}
