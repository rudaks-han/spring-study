package com.example.publisherspeed.controller;

import com.example.kafka.model.TextMessage;
import com.example.publisherspeed.TopicPublic;
import com.example.publisherspeed.publisher.MessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessagePublisher messagePublisher;

    @Transactional
    public void sendSync(int count) {
        for (int i = 0; i < count; i++) {
            TextMessage message = new TextMessage(i + " => " + TopicPublic.TEXT);
            messagePublisher.sendSync(message);
        }
    }

    @Transactional
    public void sendAsync(int count) {
        for (int i = 0; i < count; i++) {
            TextMessage message = new TextMessage(i + " => " + TopicPublic.TEXT);
            messagePublisher.sendAsync(message);
        }
    }
}
