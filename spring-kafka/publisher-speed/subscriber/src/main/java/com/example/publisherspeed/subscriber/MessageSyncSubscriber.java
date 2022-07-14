package com.example.publisherspeed.subscriber;

import com.example.kafka.model.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Slf4j
@Component
@KafkaListener(
    topics = "publisher-speed-sync",
    groupId = "publisher-speed-sync-group"
)
@RequiredArgsConstructor
public class MessageSyncSubscriber {

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
