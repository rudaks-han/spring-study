package com.example.app2.subscriber;

import com.example.app2.model.AdminMessage;
import com.example.app2.model.FileMessage;
import com.example.app2.model.Message;
import com.example.app2.model.TextMessage;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Component
@KafkaListener(
    topics = "kafka-test-message",
    groupId = "kafka-test-group"
)
public class MessageSubscriber {

    @KafkaHandler
    public void handleText(TextMessage textMessage) {
        System.out.println("textMessage");
    }

    @KafkaHandler
    public void handleFile(FileMessage fileMessage) {
        System.out.println("fileMessage");
    }

    /*@KafkaHandler
    public void handle(AdminMessage adminMessage) {
        System.out.println("adminMessage");
    }*/

    @KafkaHandler(isDefault = true)
    public void ignore(JsonSerializable jsonSerializable) {
        System.out.println("ignore");
    }
}
