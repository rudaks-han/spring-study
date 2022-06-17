package com.example.app2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spectra.attic.coreasset.share.exception.rest.factory.RestMessageConverterFactory;

@Configuration
public class Config {

    @Bean
    public RestMessageConverterFactory restMessageConverterFactory() {
        return throwable -> null;
    }
}
