package com.example.resilient4jthreadcount.service;

import com.example.resilient4jthreadcount.exception.BookNotFoundException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Bulkhead(name = "bookService", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "doFallback")
    public void find(String id) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] BookService#find start");
        execute(id);
        System.out.println("[" + threadName + "] BookService#find stop");
    }

    public void execute(String id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doFallback(String id, Throwable throwable) {
        System.err.println("fallback");
        System.err.println("id: " + id);
        throwable.printStackTrace();

        throw new BookNotFoundException();
    }
}
