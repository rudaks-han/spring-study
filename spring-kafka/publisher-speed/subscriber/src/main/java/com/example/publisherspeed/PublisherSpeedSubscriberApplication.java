package com.example.publisherspeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"spectra.attic", "com.example"})
public class PublisherSpeedSubscriberApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherSpeedSubscriberApplication.class, args);
    }

}
