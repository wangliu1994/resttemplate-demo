package com.example.consumer.service;

import com.example.consumer.BookCommand;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.winnie.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author : winnie
 * @date : 2019/3/16
 * @desc
 */
@Service
public class BookService1 {
    @Autowired
    RestTemplate restTemplate;

    public Book getBook2() {
        BookCommand bookCommand = new BookCommand(HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
        //同步调用
        return bookCommand.execute();
    }

    public Book getBook3() throws ExecutionException, InterruptedException {
        BookCommand bookCommand = new BookCommand(HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
        //异步调用
        Future<Book> queue = bookCommand.queue();
        return queue.get();
    }
}
