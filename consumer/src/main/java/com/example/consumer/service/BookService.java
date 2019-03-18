package com.example.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.winnie.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : winnie
 * @date : 2019/3/16
 * @desc
 */
@Service
public class BookService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public Book getBook() {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity("http://PROVIDER/getBook1", Book.class);
        return responseEntity.getBody();
    }

    public Book error() {
        return new Book("error", 0, "error", "error");
    }
}
