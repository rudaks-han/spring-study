package com.example.rebalancing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfig {
}
