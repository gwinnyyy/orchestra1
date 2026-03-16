package com.hps.orchestraspa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hps") // Force it to scan everything under com.hps
public class OrchestraspaApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrchestraspaApplication.class, args);
    }
}
