package com.example.order_my.mapper;

import com.example.order_my.domain.vo.OrderDTO;
import com.example.order_my.domain.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
//    추가
    public void insert(OrderDTO orderDTO);
//    삭제
    public void delete(String orderId);
//    상품 번호로 조회
    public List<OrderVO> selectByItemNumber(Long itemNumber);
//    주문 번호로 조회
    public List<OrderDTO> selectByOrderId(String orderId);
//    전체 조회
    public List<OrderDTO> selectAll();
//    주문 번호를 조회
    public String selectOrderId();
//    주문 번호 증가
    public void next();
//    주문 번호 추가
    public void insertOrderSequence();
}
