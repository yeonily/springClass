package com.example.order_my.domain.dao;

import com.example.order_my.domain.vo.OrderDTO;
import com.example.order_my.domain.vo.OrderVO;
import com.example.order_my.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDAO {
    private final OrderMapper orderMapper;

    //    추가
    public void save(OrderDTO orderDTO){
        orderMapper.insert(orderDTO);
    }
    //    삭제
    public void deleteById(String orderId){
        orderMapper.delete(orderId);
    }
    //    상품 번호로 조회
    public List<OrderVO> findByItemNumber(Long itemNumber){
        return orderMapper.selectByItemNumber(itemNumber);
    }
    //    주문 번호로 조회
    public List<OrderDTO> findByOrderId(String orderId){
        return orderMapper.selectByOrderId(orderId);
    }
    //    전체 조회
    public List<OrderVO> findAll(){
        return orderMapper.selectAll();
    }
    //    주문 번호를 조회
    public String findId(){
        return orderMapper.selectOrderId();
    }
    //    주문 번호 증가
    public void setOrderSequence(){
        orderMapper.next();
    }
    //    주문 번호 추가
    public void saveOrderSequence(){
        orderMapper.insertOrderSequence();
    }
}
