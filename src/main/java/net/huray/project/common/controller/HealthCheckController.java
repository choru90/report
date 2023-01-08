package net.huray.project.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class HealthCheckController {

    @GetMapping(value = "/")
    public String health()  {
        return logging();
    }
    
    @GetMapping(value = "/health-check")
    public String healthCheck()  {
        return logging();
    }

    private String logging() {
        return "{\"time\":\"" + ZonedDateTime.now() + "\"}";
    }
}
