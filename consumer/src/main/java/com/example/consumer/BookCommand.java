package com.example.consumer;

import com.netflix.hystrix.HystrixCommand;
import com.winnie.Book;
import org.springframework.web.client.RestTemplate;

/**
 * @author : winnie
 * @date : 2019/3/18
 * @desc
 */
public class BookCommand extends HystrixCommand<Book> {
    private RestTemplate restTemplate;

    protected BookCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected Book run() throws Exception {
        return null;
    }

    @Override
    protected Book getFallback() {
        return new Book("宋诗选注", 88, "钱钟书", "三联书店");
    }
}
