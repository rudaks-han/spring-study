package com.example.rebalancing.subscriber;

import com.example.rebalancing.model.TextMessage;
import com.example.share.util.JsonSerializable;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
    topics = "kafka-test-rebalancing",
    groupId = "kafka-test-rebalancing-group"
)
public class MessageSubscriber {

    private String name = "subscriber2";

    @KafkaHandler
    public void handle(TextMessage textMessage) {
        System.err.println("[" + name + "] message received: " + textMessage.toJson());

        process(textMessage);

        //System.err.println("[" + name + "] request commit");
    }

    private void process(TextMessage textMessage) {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.err.println("message processed");
    }

    @KafkaHandler(isDefault = true)
    public void ignore(JsonSerializable jsonSerializable) {
        System.out.println("ignore");
    }
}
