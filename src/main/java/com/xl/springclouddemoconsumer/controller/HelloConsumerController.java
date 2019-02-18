package com.xl.springclouddemoconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.Future;

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

    public String hystrix(Throwable throwable){
        System.out.println(throwable.getMessage());
        System.out.println(throwable.fillInStackTrace());
        System.out.println(throwable.toString());
        return "this message from hystrix";
    }
    @GetMapping("/helloWorldFuture")
    @HystrixCommand(fallbackMethod = "hystrix")
    public Future<String> helloWorldFuture(){
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://SPRINGCLOUDDEMOSERVICE/hello/helloworld",String.class);
            }
        };
    }


}
