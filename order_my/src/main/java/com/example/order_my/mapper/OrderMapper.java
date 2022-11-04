package com.example.order_my.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
//    추가
    public void insert();
//    삭제
    public void delete();
//    상품 번호로 조회
    public void selectByItemNumber();
//    주문 번호로 조회
    public void selectByOrderId();
//    전체 조회
    public void selectAll();
//    주문 번호를 조회
    public void selectOrderId();
//    주문 번호 증가
    public void next();
//    주문 번호 추가
    public void insertOrderSequence();
}
