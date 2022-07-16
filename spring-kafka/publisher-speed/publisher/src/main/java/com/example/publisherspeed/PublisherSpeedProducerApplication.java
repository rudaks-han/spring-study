package com.example.publisherspeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class PublisherSpeedProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherSpeedProducerApplication.class, args);
    }

}
