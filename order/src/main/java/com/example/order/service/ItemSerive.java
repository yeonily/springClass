package com.example.order.service;

import com.example.order.domain.dao.ItemDAO;
import com.example.order.domain.dao.OrderDAO;
import com.example.order.domain.vo.ItemVO;
import com.example.order.domain.vo.OrderDTO;
import com.example.order.domain.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemSerive {
    private final ItemDAO itemDAO;


//    아이템 전체 조회
    public List<ItemVO> showAll(){
        return itemDAO.findAll();
    }

}











