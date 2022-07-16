package com.example.publisherspeed.controller;

import com.example.publisherspeed.TopicPublic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public void sendSync(@RequestParam(defaultValue = "1") int count) {
        List<Long> elapsedTimes = new ArrayList<>();

        for (int i=0; i<3; i++) {
            long start = System.currentTimeMillis();
            messageService.sendSync(count);
            long elapsedTime = System.currentTimeMillis() - start;
            System.err.println("[" + TopicPublic.TOPIC_NAME + "] " + count + " sendSync elapsedTime : " + elapsedTime + "(ms)");
            elapsedTimes.add(elapsedTime);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        double averageTime = elapsedTimes.stream().mapToLong(Long::longValue).average().getAsDouble();
        System.err.println("[" + TopicPublic.TOPIC_NAME + "] " + count + " sendSync averageTime : " + averageTime + "(ms)");
    }

    @GetMapping("async")
    public void sendAsync(@RequestParam(defaultValue = "1") int count) {
        List<Long> elapsedTimes = new ArrayList<>();

        for (int i=0; i<3; i++) {
            long start = System.currentTimeMillis();
            messageService.sendAsync(count);
            long elapsedTime = System.currentTimeMillis() - start;
            System.err.println("[" + TopicPublic.TOPIC_NAME + "] " + count + " sendASync elapsedTime : " + elapsedTime + "(ms)");
            elapsedTimes.add(elapsedTime);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        double averageTime = elapsedTimes.stream().mapToLong(Long::longValue).average().getAsDouble();
        System.err.println("[" + TopicPublic.TOPIC_NAME + "] " + count + " sendSync averageTime : " + averageTime + "(ms)");
    }
}
