package com.example.publisherspeed.model;

import java.util.UUID;

import com.example.kafka.model.TextMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SyncMessage {

    private String key ;

    private TextMessage message;

    public SyncMessage(TextMessage message) {
        this.key = UUID.randomUUID().toString();
        this.message = message;
    }
}
