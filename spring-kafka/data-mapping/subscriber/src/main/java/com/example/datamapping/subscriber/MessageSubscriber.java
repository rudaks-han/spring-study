package com.example.datamapping.subscriber;

import com.example.datamapping.model.FileMessage;
import com.example.datamapping.model.TextMessage;
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
