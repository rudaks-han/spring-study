package com.example.rebalancing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"spectra.attic", "com.example"})
public class RebalancingSubscriber2Application {

    public static void main(String[] args) {
        SpringApplication.run(RebalancingSubscriber2Application.class, args);
    }

}
