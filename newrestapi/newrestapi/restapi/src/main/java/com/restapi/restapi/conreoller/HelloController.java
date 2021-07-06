package com.restapi.restapi.conreoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {
    @GetMapping("hello")
    public String getHelloWorld(){
        return "Hello world!";
    }
}
