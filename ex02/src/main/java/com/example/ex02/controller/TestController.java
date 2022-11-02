package com.example.ex02.controller;


import com.example.ex02.mapper.TimeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/test/*")
@RequiredArgsConstructor
public class TestController {

    private final TimeMapper timeMapper;

    @RequestMapping("time")
    public void time(Model model){
        model.addAttribute("now", timeMapper.getTime());
        model.addAttribute("now", timeMapper.getTimeQuick());
    }

}
