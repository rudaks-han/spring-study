package com.example.hystrixthreadcount.service;

import com.example.hystrixthreadcount.model.User;
import com.example.hystrixthreadcount.exception.NoUserFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@EnableHystrix
public class UserService {

    @Transactional
    @HystrixCommand(
        groupKey = "userService",
        commandKey = "userService",
        threadPoolKey = "userService",
        fallbackMethod = "doFallback",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000"), // thread가 해당 시간 이상 실행이 되면 timeout이 발생하여 fallback으로 빠진다
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"), // semaphore 방식을 이용
            //@HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "1"), // semaphore 방식을 이용할 경우 최대 동시 요청 수
            //@HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "${concurrent.user}"), // semaphore 방식을 이용할 경우 최대 동시 요청 수
            //@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true") // semaphore 방식을 이용할 경우 최대 동시 요청 수
        }/*,
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"), // thread 수
                    @HystrixProperty(name = "maxQueueSize", value = "0"), // 실행 대기중인 thread개수가 해당 값을 넘어가면 fallback으로 빠진다.
            }*/
    )
    public User find(String id, String name) throws HystrixRuntimeException {
        String threadName = Thread.currentThread().getName();

        System.out.println("[" + threadName + "] UserService#find start");

        boolean result = execute(id, name);

        System.out.println("[" + threadName + "] UserService#find stop");

        return new User(id);
    }


    public boolean execute(String id, String name) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public User doFallback(String id, String name, Throwable commandException) throws Exception {
        System.err.println("fallback");
        System.err.println("id: " + id);
        //throwable.printStackTrace();

        //return new User("no_user");
        //throw new Exception("");
        //throw new RuntimeException("fallback");
        throw new NoUserFoundException();
    }

}
