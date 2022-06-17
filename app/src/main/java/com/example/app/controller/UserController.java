package com.example.app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String get() {
        return "get";
    }

    @PostMapping
    public String post() {
        return "post";
    }

    @PutMapping
    public String put() {
        return "put";
    }

}
