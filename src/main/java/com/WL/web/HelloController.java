package com.WL.web;

import com.WL.web.dto.HelloResponseDto;
import com.WL.web.dto.Info;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

    @PostMapping("/test")
    public String test(@RequestBody Info info){
        return info.getName() + "의 블로그입니다. " + info.getId();
    }

    @PostMapping("/blog")
    public String blogPost(@RequestBody Info info){
        System.out.println("하하"+info.getId()+info.getName());
        return info.getName() + "의 블로그입니다. " + info.getId();
    }

}
