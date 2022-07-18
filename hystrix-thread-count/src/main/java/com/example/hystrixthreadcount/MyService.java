package com.example.hystrixthreadcount;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @HystrixCommand(
            fallbackMethod = "doFallback",
            threadPoolProperties = {
                    //@HystrixProperty(name = "maxQueueSize", value = "1"),
                    @HystrixProperty(name = "coreSize", value = "1")
            }
    )
    public void execute() {
        System.out.printf("thread: %s\n", Thread.currentThread().getName());
        System.out.println("MyService#execute start");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyService#execute stop");
    }

    public void doFallback() {
        System.err.println("fallback");
    }

}
