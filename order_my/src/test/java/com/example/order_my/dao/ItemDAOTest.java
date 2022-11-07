package com.example.order_my.dao;

import com.example.order_my.domain.dao.ItemDAO;
import com.example.order_my.domain.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ItemDAOTest {
    @Autowired
    private ItemDAO itemDAO;

    @Test
    public void saveTest(){
        ItemVO itemVO =  new ItemVO();
        itemVO.setItemName("멜론");
        itemVO.setItemPrice(8000);
        itemVO.setItemStock(200);

        itemDAO.save(itemVO);
    }

    @Test
    public void setItemTest(){
        ItemVO itemVO = itemDAO.findById(21L);
        itemVO.setItemStock(itemVO.getItemStock() - 100);

        itemDAO.setItem(itemVO);
    }

    @Test
    public void findByIdTest(){
        ItemVO itemVO = itemDAO.findById(21L);
        log.info(itemVO.toString());
    }

    @Test
    public void findAllTest(){
        itemDAO.findAll().stream().map(itemVO -> itemVO.toString()).forEach(log::info);
    }
}
