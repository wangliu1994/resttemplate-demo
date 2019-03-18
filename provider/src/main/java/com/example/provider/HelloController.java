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

    //---> /hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        List<ServiceInstance> instances = client.getInstances("provider");
        for (ServiceInstance instance : instances) {
            logger.info("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
        }
        return "Hello World";
    }

    //---> /hello1/winnie
    @RequestMapping(value = "/hello1/{name}", method = RequestMethod.GET)
    public String hello1(@PathVariable("name") String name) {
        List<ServiceInstance> instances = client.getInstances("provider");
        for (ServiceInstance instance : instances) {
            logger.info("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
        }
        if (name == null || name.isEmpty()) {
            return "Hello World";
        } else {
            return "Hello " + name;
        }
    }

    //---> /hello1?name=winnie
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello2(@RequestParam(name = "name") String name) {
        List<ServiceInstance> instances = client.getInstances("provider");
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
