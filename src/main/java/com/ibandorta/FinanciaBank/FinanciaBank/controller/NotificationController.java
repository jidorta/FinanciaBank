package com.ibandorta.FinanciaBank.FinanciaBank.controller;

import com.ibandorta.FinanciaBank.FinanciaBank.model.Notification;
import com.ibandorta.FinanciaBank.FinanciaBank.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification){
        if(notification.getType() == null){
            notification.setType("INFO");
        }
        if (notification.getTimestamp() == null) {
            notification.setTimestamp(LocalDateTime.now());
        }
        return service.save(notification);

    }

    @GetMapping
    public List<Notification>getAllNotifications(){
        return service.findAll();
    }
}
