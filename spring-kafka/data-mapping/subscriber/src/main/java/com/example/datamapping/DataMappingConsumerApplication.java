package com.example.datamapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"spectra.attic", "com.example"})
public class DataMappingConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataMappingConsumerApplication.class, args);
    }
}
