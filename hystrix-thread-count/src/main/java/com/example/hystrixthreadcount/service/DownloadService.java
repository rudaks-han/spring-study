package com.example.hystrixthreadcount.service;

import com.example.hystrixthreadcount.exception.DownloadFailedException;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolMetrics;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DownloadService {

    // SEMAPHORE 방식을 적용
    @HystrixCommand(
        groupKey = "downloadService", // 기본값은 클래스명이다.
        commandKey = "downloadCommandKey", // 따로 설정되지 않으면 메소드명으로 설정되고 동일한 메소드명이 있으면 오동작할 수 있으니 반드시 설정하자. 또한 application.yml에 설정을 추가할 때 사용한다.
        threadPoolKey = "downloadThreadPoolKey", // threadPool별로 다르게 설정을 사용할 경우에 적용
        fallbackMethod = "downloadFailed", // fallback이 실행될 메소드 명
        ignoreExceptions = { RuntimeException.class }, // exception으로 제외할 클래스
        commandProperties = {
                ///@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000"), // thread가 해당 시간 이상 실행이 되면 timeout이 발생하여 fallback으로 빠진다
                @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE") // semaphore 방식을 이용
                //@HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "1") // semaphore 방식을 이용할 경우 최대 동시 요청 수
        }
    )
    public String download(String id) {
        String threadName = Thread.currentThread().getName();
        HystrixThreadPoolMetrics instance = HystrixThreadPoolMetrics.getInstance(() -> "downloadThreadPoolKey");
        log.debug("Active thread: {}", instance.getCurrentActiveCount());
        log.debug("Core pool size: {}", instance.getCurrentCorePoolSize());
        log.debug("Maximum pool size: {}", instance.getCurrentMaximumPoolSize());
        log.debug("Current task count: {}", instance.getCurrentTaskCount());

        log.debug("[" + threadName + "] DownloadService#download start");
        execute(id);
        log.debug("[" + threadName + "] DownloadService#find stop");

        return "success";
    }

    private boolean execute(String id) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    // service 메소드의 파라미터와 리턴타입이 같아야 한다. ==> String download(String id)
    private String downloadFailed(String id, Throwable throwable) {
        // throwable : java.lang.RuntimeException: could not acquire a semaphore for execution
        // exception을 던지면 해당 메소드를 호출하면 쪽으로 전달 (DownloadController)
        throw new DownloadFailedException("concurrent request exceeded");
    }
}
