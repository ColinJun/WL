package com.WL.web;

import com.WL.web.dto.HelloResponseDto;
import com.WL.web.dto.Info;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
        return "footer";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

    @PostMapping("/test")
    public String test(@ModelAttribute("ggggg") Info info){
        return info.getName() + " AND " + info.getId();
    }

    @PostMapping("/test2")
    public String blogPost(@RequestBody Info info){
        return info.getName() + " AND " + info.getId();
    }
}
