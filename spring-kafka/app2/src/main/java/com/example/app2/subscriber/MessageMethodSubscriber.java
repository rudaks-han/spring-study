package com.example.app2.subscriber;

import com.example.app2.model.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageMethodSubscriber {

    @KafkaListener(
        topics = "kafka-test-message",
        groupId = "kafka-test-group-method",
        errorHandler = "messageErrorHandler"
    )
    public void handle2(@Payload Message message) {

        System.out.println("jsonSerialize");
    }

    /*@KafkaListener(
        topics = "kafka-test-message",
        groupId = "kafka-test-group"
    )
    public void handle(Message message) {

    }*/
   /* @KafkaHandler
    public void handle(TextMessage textMessage) {
        System.out.println("textMessage");
    }

    @KafkaHandler
    public void handle(FileMessage fileMessage) {
        System.out.println("fileMessage");
    }

    @KafkaHandler(isDefault = true)
    public void ignore(JsonSerializable jsonSerializable) {
        System.out.println("ignore");
    }*/

    /*@KafkaListener(
        topics = "kafka-test-message",
        groupId = "kafka-test-group"
    )
    public void handle(@Header(value = "__TypeId__") String typeId, @Payload TextMessage message) {

        System.out.println("message");
    }*/

    /*@KafkaListener(
        topics = "kafka-test-message",
        groupId = "kafka-test-group"
    )
    public void handle(@Payload Message message) {

        System.out.println("message");
    }
*/

    @Bean
    public KafkaListenerErrorHandler messageErrorHandler() {

        System.out.println("messageError");
        return (message, error) -> {
            System.out.println("message : " + message);
            System.out.println("error : " + error);

            ConsumerRecord<String, String> record = (ConsumerRecord<String, String>) message.getPayload();

            return record.value();
        };
    }
}
