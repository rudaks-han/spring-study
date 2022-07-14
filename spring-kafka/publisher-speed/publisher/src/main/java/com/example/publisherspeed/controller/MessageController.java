package com.example.publisherspeed.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.kafka.model.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spectra.attic.coreasset.share.util.JsonSerializable;

@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public void sendSync(@RequestParam(defaultValue = "1") int count) {
        long start = System.currentTimeMillis();

        messageService.sendSync(count);

        long elapsedTime = System.currentTimeMillis() - start;
        System.err.println(count + " sendSync elapsedTime : " + elapsedTime + "(ms)");
    }

    @GetMapping("async")
    public void sendAsync(@RequestParam(defaultValue = "1") int count) {
        long start = System.currentTimeMillis();

        messageService.sendAsync(count);

        long elapsedTime = System.currentTimeMillis() - start;
        System.err.println(count + " sendASync elapsedTime : " + elapsedTime + "(ms)");
    }
}
