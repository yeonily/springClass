package com.example.order_my.controller;

import com.example.order_my.domain.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
public class OrderControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/order/add")
                .param("orders[0].itemNumber", "4")
                .param("orders[0].itemCount", "5")
                .param("orders[1].itemNumber", "5")
                .param("orders[1].itemCount", "5")).andReturn();
    }
}
