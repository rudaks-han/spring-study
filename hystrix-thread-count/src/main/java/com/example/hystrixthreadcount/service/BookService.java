package com.example.hystrixthreadcount.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {


    @Transactional
    @HystrixCommand(
        groupKey = "bookService",
        commandKey = "bookService",
        threadPoolKey = "bookService",
        fallbackMethod = "doFallback",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100000"), // thread가 해당 시간 이상 실행이 되면 timeout이 발생하여 fallback으로 빠진다
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"), // semaphore 방식을 이용
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "5") // semaphore 방식을 이용할 경우 최대 동시 요청 수
        }/*,
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"), // thread 수
                    @HystrixProperty(name = "maxQueueSize", value = "0"), // 실행 대기중인 thread개수가 해당 값을 넘어가면 fallback으로 빠진다.
            }*/
    )
    public void find(String id) {
        String threadName = Thread.currentThread().getName();

        System.out.println("[" + threadName + "] BookService#find start");

        boolean result = execute(id);

        System.out.println("[" + threadName + "] BookService#find stop");
    }

    public boolean execute(String id) {
        try {
            Thread.sleep(10000);
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
