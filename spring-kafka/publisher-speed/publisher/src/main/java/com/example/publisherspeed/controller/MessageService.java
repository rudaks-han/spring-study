package com.example.publisherspeed.controller;

import com.example.kafka.model.TextMessage;
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

    private final String text = "추경호 경제부총리 겸 기획재정부 장관이 경기 침체 극복을 위한 방안으로 법인세·종부세 대폭 완화, 가업승계 부담 완화 방안 등을 발표했습니다.";
    @Transactional
    public void sendSync(int count) {
        for (int i = 0; i < count; i++) {
            TextMessage message = new TextMessage(i + " => " + text);
            messagePublisher.sendSync(message);
        }
    }

    @Transactional
    public void sendAsync(int count) {
        for (int i = 0; i < count; i++) {
            TextMessage message = new TextMessage(i + " => " + text);
            messagePublisher.sendAsync(message);
        }
    }
}
