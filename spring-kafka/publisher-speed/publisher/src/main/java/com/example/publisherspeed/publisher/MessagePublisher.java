package com.example.publisherspeed.publisher;

import java.util.concurrent.ExecutionException;

import com.example.kafka.model.TextMessage;
import com.example.publisherspeed.model.AsyncMessage;
import com.example.publisherspeed.model.SyncMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Component
@RequiredArgsConstructor
public class MessagePublisher {

    private final KafkaTemplate<String, JsonSerializable> kafkaTemplate;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void sendSync(TextMessage message) {
        applicationEventPublisher.publishEvent(new SyncMessage(message));
    }

    @Transactional
    public void sendAsync(TextMessage message) {
        applicationEventPublisher.publishEvent(new AsyncMessage(message));
    }

    @TransactionalEventListener
    public void doAfterCommit(SyncMessage syncMessage) {
        try {
            kafkaTemplate.send("publisher-speed-sync", syncMessage.getKey(), syncMessage.getMessage()).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @TransactionalEventListener
    public void doAfterCommit(AsyncMessage asyncMessage) {
        kafkaTemplate.send("publisher-speed-sync", asyncMessage.getKey(), asyncMessage.getMessage());
    }
}
