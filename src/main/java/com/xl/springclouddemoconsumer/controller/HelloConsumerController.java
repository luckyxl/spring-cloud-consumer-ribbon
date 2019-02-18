package com.xl.springclouddemoconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName HelloService
 * @Description springcloud消费者测试类
 * @Author xule
 * @Date 2019/2/12 14:00
 * @Version 1.0
 **/
@RestController
@RequestMapping("/helloconsumer")
public class HelloConsumerController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/helloworld")
    @HystrixCommand(fallbackMethod = "hystrix")
    public String helloWorld(){
        return restTemplate.getForObject("http://SPRINGCLOUDDEMOSERVICE/hello/helloworld",String.class);
    }

    public String hystrix(){
        return "this message from hystrix";
    }


}
