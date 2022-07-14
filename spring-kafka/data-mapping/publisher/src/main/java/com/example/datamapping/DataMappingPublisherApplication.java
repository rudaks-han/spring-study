package com.example.datamapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"spectra.attic", "com.example"})
public class DataMappingPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataMappingPublisherApplication.class, args);
    }

}
