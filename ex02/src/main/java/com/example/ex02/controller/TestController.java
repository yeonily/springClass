package com.example.ex02.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/test1/*")
public class TestController {

    @RequestMapping("test1")
    public void test1(){
        log.info("test1..........");
    }

    @RequestMapping("test1")
    public String test2(){
        log.info("test2..........");
        return "test/join";
    }

    @GetMapping("ex02")
    public void ex02(){

    }

    @PostMapping("ex02")
    public void ex03(){

    }

}
