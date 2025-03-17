package com.asap.Login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("greet")
    public String sendGreet(){
        return "Hello! Welcome to ASAP";
    }
}
