package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : winnie
 * @date : 2019/3/15
 * @desc
 */
@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/get-hello")
    public String helloController() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://PROVIDER/hello", String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer();
        result.append("responseEntity.getBody()：").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
        return result.toString();
    }

    @RequestMapping("/sayhello1")
    public String sayHello() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://PROVIDER/hello1/{1}", String.class, "张三");
        return responseEntity.getBody();
    }

    @RequestMapping("/sayhello2/{name}")
    public String sayHello2(@PathVariable("name") String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://PROVIDER/hello1/{name}", String.class, map);
        return responseEntity.getBody();
    }

    @RequestMapping("/sayhello3")
    public String sayHello3(@RequestParam(name = "name") String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://PROVIDER/hello1?name={name}", String.class, map);
        return responseEntity.getBody();
    }
}
