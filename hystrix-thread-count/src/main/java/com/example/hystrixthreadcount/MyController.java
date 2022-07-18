package com.example.hystrixthreadcount;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @GetMapping
    public void find() {
        myService.execute();
    }
}
