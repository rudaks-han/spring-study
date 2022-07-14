package com.example.datamapping.controller;

import com.example.datamapping.model.AdminMessage;
import com.example.datamapping.model.FileMessage;
import com.example.datamapping.model.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spectra.attic.coreasset.share.util.JsonSerializable;

@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaTemplate<String, JsonSerializable> kafkaTemplate;

    @PostMapping("text")
    public void sendText() {
        kafkaTemplate.send("kafka-test-message", new TextMessage("text"));
    }

    @PostMapping("file")
    public void sendFile() {
        kafkaTemplate.send("kafka-test-message", new FileMessage("file"));
    }

    @PostMapping("admin")
    public void sendAdmin() {
        kafkaTemplate.send("kafka-test-message", new AdminMessage("admin"));
    }
}
