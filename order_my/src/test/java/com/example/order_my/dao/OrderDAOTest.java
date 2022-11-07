package com.example.order_my.dao;

import com.example.order_my.domain.dao.ItemDAO;
import com.example.order_my.domain.dao.OrderDAO;
import com.example.order_my.domain.vo.OrderDTO;
import com.example.order_my.domain.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class OrderDAOTest {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ItemDAO itemDAO;


    @Test
    public void saveTest(){
        //주문을 추가할 때마다 주문 번호 +1
        orderDAO.setOrderSequence();
        //필요한 부분들을 DTO에 담아서 DTO를 사용한다.
        List<OrderDTO> orders = new ArrayList<>();
        OrderDTO order1 = new OrderDTO(orderDAO.findId());
        //화면에서 받아오는 정보들, 미리 set이 되어 있어야 한다.
        order1.setItemNumber(21l);
        order1.setItemCount(2);

        OrderDTO order2 = new OrderDTO(orderDAO.findId()); //각각 new를 해야 주소가 겹치지 않는다.
        order2.setItemNumber(8l);
        order2.setItemCount(2);

        orders.add(order1);
        orders.add(order2);

        for (OrderDTO order : orders){
            order.setItemPrice(itemDAO.findById(order.getItemNumber()).getItemPrice());
            order.setOrderPrice();

            orderDAO.save(order);
        }
    }

    @Test
    public void deleteByIdTest(){
        orderDAO.deleteById("2022110616");
    }

    @Test
    public void findByItemNumberTest(){
        log.info("전체 감자 판매 개수 : " + orderDAO.findByItemNumber(2l).stream().map(order -> order.getItemCount())
                .reduce(0, (count1, count2) -> count1 + count2));
    }

    @Test
    public void findByOrderIdTest(){
        orderDAO.findByOrderId("2022110316").forEach(order -> log.info("취소된" + order.getItemName() + "의 개수 : " + order.getItemCount()));
    }

    @Test
    public void findAllTest(){
        //log.info는 문자열밖에 출력이 안됨. 직접 toString 붙이고 나서 출력해준다.
        orderDAO.findAll().stream().map(OrderVO::toString).forEach(log::info);
    }
}
