package com.example.kafka.config;

import java.util.Collections;
import java.util.Map;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.MicrometerConsumerListener;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;
import spectra.attic.coreasset.share.util.FilterableMessage;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
    private final KafkaProperties kafkaProperties;

    private final MeterRegistry meterRegistry;

    private final KafkaTemplate<String, JsonSerializable> kafkaTemplate;

    @Bean
    public ConsumerFactory<String, JsonSerializable> consumerFactory() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        DefaultKafkaConsumerFactory<String, JsonSerializable> cf = new DefaultKafkaConsumerFactory<>(props);
        cf.addListener(new MicrometerConsumerListener<>(meterRegistry));
        return cf;
    }

    @Bean
    public ConsumerFactory<String, FilterableMessage> consumerFilterFactory() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        DefaultKafkaConsumerFactory<String, FilterableMessage> cf = new DefaultKafkaConsumerFactory<>(props);
        cf.addListener(new MicrometerConsumerListener<>(meterRegistry));
        return cf;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, JsonSerializable> kafkaListenerContainerFactory(
        ConsumerFactory<String, JsonSerializable> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, JsonSerializable> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
        factory.setErrorHandler(new SeekToCurrentErrorHandler(deadLetterPublishingRecoverer(), new FixedBackOff(1000L, 9L)));
        return factory;
    }

    private DeadLetterPublishingRecoverer deadLetterPublishingRecoverer() {
        return new DeadLetterPublishingRecoverer(
            Collections.singletonMap(String.class, kafkaTemplate),
            (consumerRecord, exception) -> new TopicPartition(consumerRecord.topic() + ".dlt", consumerRecord.partition())
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, JsonSerializable> batchKafkaListenerContainerFactory(
        ConsumerFactory<String, JsonSerializable> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, JsonSerializable> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FilterableMessage> filterKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, FilterableMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFilterFactory());
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
        factory.setBatchListener(true);
        factory.setRecordFilterStrategy(consumerRecord -> consumerRecord.value().filter());
        return factory;
    }
}
