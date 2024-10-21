package com.github.eulerv.picpaydesafiobackend.scheduler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/health")
public class HealthController {
  @GetMapping("path")
  public String healthStatus() {
      return new String("Application is running.");
  }
}