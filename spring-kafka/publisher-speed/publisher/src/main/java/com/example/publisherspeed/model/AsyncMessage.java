package com.example.publisherspeed.model;

import java.util.UUID;

import com.example.kafka.model.TextMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AsyncMessage {

    private String key;

    private TextMessage message;

    public AsyncMessage(TextMessage message) {
        this.key = UUID.randomUUID().toString();
        this.message = message;
    }
}
