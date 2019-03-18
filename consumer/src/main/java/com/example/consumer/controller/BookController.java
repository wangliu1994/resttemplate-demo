package com.example.consumer.controller;

import com.example.consumer.BookService;
import com.winnie.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : winnie
 * @date : 2019/3/15
 * @desc
 */
@RestController
public class BookController {
    @Autowired
    BookService service;

    @RequestMapping("/getBook1")
    public Book getBook() {
        return service.getBook();
    }
}
