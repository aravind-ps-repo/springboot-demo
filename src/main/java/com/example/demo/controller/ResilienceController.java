package com.example.demo.controller;

import com.example.demo.service.external.ExternalServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResilienceController {

    private final ExternalServiceClient client;

    @Autowired
    public ResilienceController(ExternalServiceClient client) {
        this.client = client;
    }

    @GetMapping("/api/ping-external")
    public String pingExternal() {
        return client.callExternal();
    }
}
