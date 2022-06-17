package com.example.app1.controller;

import com.example.app1.model.FileMessage;
import com.example.app1.model.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spectra.attic.coreasset.share.messaging.publisher.KafkaPublisher;

@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaPublisher kafkaPublisher;

    @PostMapping("text")
    public void sendText() {
        kafkaPublisher.send("kafka-test-message", new TextMessage("text"));
    }

    @PostMapping("file")
    public void sendFile() {
        kafkaPublisher.send("kafka-test-message", new FileMessage("file"));
    }
}
