package com.example.myadvanced.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Search {
    private String name;
    private Integer age;
}
