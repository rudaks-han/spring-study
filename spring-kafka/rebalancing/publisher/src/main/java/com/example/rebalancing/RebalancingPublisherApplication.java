package com.example.rebalancing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"spectra.attic", "com.example"})
public class RebalancingPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(RebalancingPublisherApplication.class, args);
    }

}
