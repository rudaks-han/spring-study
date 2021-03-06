package com.example.hystrixthreadcount.controller;

import com.example.hystrixthreadcount.service.BookService;
import com.example.hystrixthreadcount.exception.NoUserFoundException;
import com.example.hystrixthreadcount.service.UserService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final BookService bookService;

    @GetMapping
    public void find() {
        try {
            userService.find("rudaks", "rudaks");
        } catch (HystrixRuntimeException e) {
            if (e.getFailureType() == HystrixRuntimeException.FailureType.REJECTED_SEMAPHORE_EXECUTION) {
                throw new NoUserFoundException();
            } else {
                throw e;
            }
        }
    }

    @GetMapping("book")
    public void findBook() {
        bookService.find("rudaks");
    }
}
