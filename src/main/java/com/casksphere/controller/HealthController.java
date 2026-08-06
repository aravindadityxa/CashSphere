package com.cashsphere.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Hidden
public class HealthController {

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("CashSphere API is running");
    }
}
