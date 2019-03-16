package com.example.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author : winnie
 * @date : 2019/3/13
 * @desc
 */
@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());
    @Autowired
    private DiscoveryClient client;

    //---> /hello/winnie
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello1(@PathVariable("name") String name) {
        List<ServiceInstance> instances = client.getInstances("test-service");
        for (ServiceInstance instance : instances) {
            logger.info("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
        }
        if (name == null || name.isEmpty()) {
            return "Hello World";
        } else {
            return "Hello " + name;
        }
    }

    //---> /hello?name=winnie
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello2(@RequestParam(name = "name") String name) {
        List<ServiceInstance> instances = client.getInstances("test-service");
        for (ServiceInstance instance : instances) {
            logger.info("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
        }
        if (name == null || name.isEmpty()) {
            return "Hello World";
        } else {
            return "Hello " + name;
        }
    }
}
