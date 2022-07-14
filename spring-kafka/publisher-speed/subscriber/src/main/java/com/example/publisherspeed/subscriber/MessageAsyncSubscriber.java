package com.example.publisherspeed.subscriber;

import com.example.kafka.model.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Component
@KafkaListener(
    topics = "publisher-speed-async",
    groupId = "publisher-speed-async-group"
)
@Slf4j
@RequiredArgsConstructor
public class MessageAsyncSubscriber {

    private String name = "async-subscriber";

    @KafkaHandler
    public void handle(TextMessage textMessage) {
        log.info("[" + name + "] message received: " + textMessage.toJson());
    }

    @KafkaHandler(isDefault = true)
    public void ignore(JsonSerializable jsonSerializable) {
        System.out.println("ignore");
    }
}
