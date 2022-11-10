package com.example.order_my.controller;

import com.example.order_my.domain.vo.OrderVO;
import com.example.order_my.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order/*")
public class OrderController {
    private final OrderService orderService;

//    결제
    @GetMapping("add")
    public void add(OrderVO order){
        orderService.addOrder(order.getOrders());
    }
//    취소
//    조회
}
