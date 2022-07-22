package com.example.hystrixthreadcount.controller;

import java.util.UUID;

import com.example.hystrixthreadcount.exception.DownloadFailedException;
import com.example.hystrixthreadcount.exception.ExecutionTimeoutException;
import com.example.hystrixthreadcount.service.TimeoutService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("timeout")
@RequiredArgsConstructor
public class TimeoutController {

    private final TimeoutService timeoutService;

    @GetMapping
    public String check() {
        try {
            return timeoutService.check(UUID.randomUUID().toString());
        } catch (HystrixRuntimeException e) {
            if (e.getFailureType() == HystrixRuntimeException.FailureType.TIMEOUT) {
                throw new ExecutionTimeoutException(e.getFallbackException().getMessage());
            } else {
                throw e;
            }
        }
    }
}
