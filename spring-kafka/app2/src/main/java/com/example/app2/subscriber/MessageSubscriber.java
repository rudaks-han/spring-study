package com.example.app2.subscriber;

import com.example.app2.model.FileMessage;
import com.example.app2.model.TextMessage;
import com.fasterxml.jackson.databind.JsonSerializable;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
        topics = "kafka-test-message",
        groupId = "kafka-test-group"
)
public class MessageSubscriber {

    @KafkaHandler
    public void handle(TextMessage textMessage) {
        System.out.println("textMessage");
    }

    @KafkaHandler
    public void handle(FileMessage fileMessage) {
        System.out.println("fileMessage");
    }

    @KafkaHandler(isDefault = true)
    public void ignore(JsonSerializable jsonSerializable) {
        System.out.println("ignore");
    }
}
