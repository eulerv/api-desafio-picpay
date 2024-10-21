package com.github.eulerv.picpaydesafiobackend.scheduler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
  @GetMapping
  public ResponseEntity<String> healthStatus() {
      return ResponseEntity.ok("Application is running.");
  }
}