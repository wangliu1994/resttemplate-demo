package com.example.consumer.controller;

import com.example.consumer.service.BookService;
import com.example.consumer.service.BookService1;
import com.winnie.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author : winnie
 * @date : 2019/3/15
 * @desc
 */
@RestController
public class BookController {
    @Autowired
    BookService service;

    @Autowired
    BookService1 service1;

    @RequestMapping("/getBook1")
    public Book getBook1() {
        return service.getBook();
    }

    @RequestMapping("/getBook2")
    public Book getBook2() {
        return service1.getBook2();
    }

    @RequestMapping("/getBook3")
    public Book getBook3() throws ExecutionException, InterruptedException {
        return service1.getBook3();
    }
}
