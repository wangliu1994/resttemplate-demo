package com.example.provider;

import com.winnie.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : winnie
 * @date : 2019/3/16
 * @desc
 */
@RestController
public class BookController {

    @GetMapping("getBook1")
    public Book getBook(){
        return new Book("三国演义", 90, "罗贯中", "花城出版社");
    }
}
