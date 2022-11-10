package com.example.ex02.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class MemberVO {
    private String name;
    private int age;

    private List<MemberVO> members;
    //VO는 list로 받을 수가 없어서 필드 안에서 조회를 해야 함.
}