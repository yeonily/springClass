package com.example.ex02.controller;


import com.example.ex02.domain.vo.MemberVO;
import com.example.ex02.mapper.TimeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @GetMapping("ex08")
    public void ex08(@RequestParam("data") List<String> datas){
        //log.info(datas.toString());
        for(String data: datas){
            log.info(data);
        }
    }
    //래퍼클래스는 list로 받을 수 있는데, vo는 list로 받을 수 없음.
    //vo 필드 안에서 list 조회 해야 함.

    @GetMapping("ex09/form")
    public String ex09Form(){
        return "/test/ex09";
    }

    @GetMapping("ex09")
    public void ex09(MemberVO member){
        log.info(member.getMembers().toString());
    }
}
