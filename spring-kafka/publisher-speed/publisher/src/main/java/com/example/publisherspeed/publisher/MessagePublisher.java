package com.example.publisherspeed.publisher;

import com.example.kafka.model.TextMessage;
import com.example.publisherspeed.TopicPublic;
import com.example.share.util.JsonSerializable;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class MessagePublisher {

    private final KafkaTemplate<String, JsonSerializable> kafkaTemplate;

    @Transactional
    public void sendSync(TextMessage message) {
        try {
            String key = UUID.randomUUID().toString();
            kafkaTemplate.send(TopicPublic.TOPIC_NAME, key, message).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void sendAsync(TextMessage message) {
        String key = UUID.randomUUID().toString();
        kafkaTemplate.send(TopicPublic.TOPIC_NAME, key, message);
    }
}
