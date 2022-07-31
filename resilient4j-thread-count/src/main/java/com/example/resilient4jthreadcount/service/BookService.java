package com.example.resilient4jthreadcount.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Bulkhead(name = "bookService", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "doFallback")
    public void find(String id) {
        String threadName = Thread.currentThread().getName();

        System.out.println("[" + threadName + "] BookService#find start");

        boolean result = execute(id);

        System.out.println("[" + threadName + "] BookService#find stop");
    }

    public boolean execute(String id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public void doFallback(String id, Throwable throwable) {
        System.err.println("fallback");
        System.err.println("id: " + id);
        throwable.printStackTrace();
    }

}
