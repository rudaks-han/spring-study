package com.example.app.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String get(
            @RequestHeader(required = false, value = "customHeader") String customHeader,
            @RequestBody(required = false) Map<String, String> content
    ) {
        return result("get", customHeader, content);
    }

    @PostMapping
    public String post(
            @RequestHeader(required = false, value = "customHeader") String customHeader,
            @RequestBody(required = false)  Map<String, String> content
    ) {
        return result("post", customHeader, content);
    }

    @PutMapping
    public String put(
            @RequestHeader(required = false, value = "customHeader") String customHeader,
            @RequestBody(required = false)  Map<String, String> content
    ) {
        return result("put", customHeader, content);
    }

    @DeleteMapping
    public String delete(
            @RequestHeader(required = false, value = "customHeader") String customHeader,
            @RequestBody(required = false)  Map<String, String> content
    ) {
        return result("delete", customHeader, content);
    }

    private String result(String method, String customHeader, Map<String, String> content) {
        return "[호출 결과]\n"
                + "method: " + method + "\n"
                + "customHeader: " + customHeader + "\n"
                + "content: " + (content == null ? "null" : content);
    }
}
