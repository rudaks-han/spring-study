package com.example.publisherspeed.controller;

import com.example.publisherspeed.TopicPublic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        System.err.println("[" + TopicPublic.TOPIC_NAME + "] " + count + " sendSync elapsedTime : " + elapsedTime + "(ms)");
    }

    @GetMapping("async")
    public void sendAsync(@RequestParam(defaultValue = "1") int count) {
        long start = System.currentTimeMillis();

        messageService.sendAsync(count);

        long elapsedTime = System.currentTimeMillis() - start;
        System.err.println("[" + TopicPublic.TOPIC_NAME + "] " + count + " sendASync elapsedTime : " + elapsedTime + "(ms)");
    }
}
