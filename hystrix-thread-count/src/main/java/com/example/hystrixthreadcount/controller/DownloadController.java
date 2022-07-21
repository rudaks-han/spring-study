package com.example.hystrixthreadcount.controller;

import com.example.hystrixthreadcount.exception.DownloadFailedException;
import com.example.hystrixthreadcount.service.DownloadService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("download")
@RequiredArgsConstructor
public class DownloadController {

    private final DownloadService downloadService;

    @GetMapping
    public String download() {
        try {
            return downloadService.download(UUID.randomUUID().toString());
        } catch (HystrixRuntimeException e) {
            if (e.getFailureType() == HystrixRuntimeException.FailureType.REJECTED_SEMAPHORE_EXECUTION) {
                throw new DownloadFailedException(e.getFallbackException().getMessage());
            } else {
                throw e;
            }
        }
    }
}
