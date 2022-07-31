package com.example.resilient4jthreadcount.controller;

import com.example.resilient4jthreadcount.exception.BookNotFoundException;
import com.example.resilient4jthreadcount.service.BookService;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public void find() {
        bookService.find("rudaks");
    }
}
