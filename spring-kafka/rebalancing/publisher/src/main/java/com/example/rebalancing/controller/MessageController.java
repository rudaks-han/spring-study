package com.example.rebalancing.controller;

import java.util.concurrent.atomic.AtomicInteger;

import com.example.rebalancing.model.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spectra.attic.coreasset.share.util.JsonSerializable;

@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaTemplate<String, JsonSerializable> kafkaTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger();

    @GetMapping
    public void send(@RequestParam(defaultValue = "1") int count) {
        for (int i = 0; i < count; i++) {
            TextMessage message = new TextMessage("text " + atomicInteger.getAndIncrement());
            kafkaTemplate.send("kafka-test-rebalancing", message);
            System.out.println("KafkaTemplate.send: " + message.toJson());
        }
    }
}
