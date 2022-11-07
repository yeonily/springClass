package com.example.order_my.service;

import com.example.order_my.domain.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void addOrderTest(){
        List<OrderVO> orders = new ArrayList<>();
        OrderVO order1 = new OrderVO();
        order1.setItemNumber(4L);
        order1.setItemCount(10);

        OrderVO order2 = new OrderVO();
        order2.setItemNumber(5L);
        order2.setItemCount(10);

        orders.add(order1);
        orders.add(order2);

        orderService.addOrder(orders);

    }

    @Test
    public void cancelTest(){
        String orderId = "2022110616";
        orderService.cancel(orderId);
    }

//    조회 테스트
   @Test
    public void showTest(){

    }

//    전체 조회 테스트

}