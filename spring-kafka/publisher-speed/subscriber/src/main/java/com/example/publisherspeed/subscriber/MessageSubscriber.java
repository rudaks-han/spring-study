package com.example.publisherspeed.subscriber;

import com.example.kafka.model.TextMessage;
import com.example.share.util.JsonSerializable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(
    topics = "topic-partition-3",
    groupId = "publisher-speed-sync-group"
)
@RequiredArgsConstructor
public class MessageSubscriber {

    private String name = "sync-subscriber";

    @KafkaHandler
    public void handle(TextMessage textMessage) {
        //System.err.println("[" + name + "] message received: " + textMessage.toJson());
        log.info("[" + name + "] message received: " + textMessage.toJson());
    }

    @KafkaHandler(isDefault = true)
    public void ignore(JsonSerializable jsonSerializable) {
        System.out.println("ignore");
    }
}
