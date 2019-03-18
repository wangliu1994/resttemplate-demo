package com.example.consumer;

import com.netflix.hystrix.HystrixCommand;
import com.winnie.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author : winnie
 * @date : 2019/3/18
 * @desc
 */
public class BookCommand extends HystrixCommand<Book> {
    private RestTemplate restTemplate;

    public BookCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected Book run() {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity("http://PROVIDER/getBook1", Book.class);
        return responseEntity.getBody();
    }

    @Override
    protected Book getFallback() {
        return new Book("宋诗选注", 88, "钱钟书", "三联书店");
    }
}
