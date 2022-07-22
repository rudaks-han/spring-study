package com.example.hystrixthreadcount.controller;

import java.util.UUID;

import com.example.hystrixthreadcount.exception.DownloadFailedException;
import com.example.hystrixthreadcount.service.DownloadService;
import com.example.hystrixthreadcount.service.WaitingService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("waiting")
@RequiredArgsConstructor
public class WaitingController {

    private final WaitingService waitingService;

    @GetMapping
    public String check() {
        try {
            return waitingService.check(UUID.randomUUID().toString());
        } catch (HystrixRuntimeException e) {
            if (e.getFailureType() == HystrixRuntimeException.FailureType.REJECTED_SEMAPHORE_EXECUTION) {
                throw new DownloadFailedException(e.getFallbackException().getMessage());
            } else {
                throw e;
            }
        }
    }
}
