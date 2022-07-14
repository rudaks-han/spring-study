package com.example.rebalancing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"spectra.attic", "com.example"})
public class RebalancingSubscriber1Application {

    public static void main(String[] args) {
        SpringApplication.run(RebalancingSubscriber1Application.class, args);
    }

}
