package com.example.order_my.mapper;

import com.example.order_my.domain.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.common.FailoverStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ItemMapperTest {
    @Autowired
    private ItemMapper itemMapper; //생성자주입X 필드주입

    @Test
    public void insertTest(){
        ItemVO itemVO =  new ItemVO();
        itemVO.setItemName("귤");
        itemVO.setItemPrice(8000);
        itemVO.setItemStock(200);

        itemMapper.insert(itemVO);
    }

    @Test
    public void updateTest(){
        ItemVO itemVO = itemMapper.select(8L);
        itemVO.setItemName("꿀");
        itemVO.setItemPrice(itemVO.getItemPrice() + 2000);
        itemVO.setItemStock(itemVO.getItemStock() + 800);

        itemMapper.update(itemVO);
    }

    @Test
    public void selectTest(){
        ItemVO itemVO = itemMapper.select(8L);
        log.info(itemVO.toString());
    }

    @Test
    public void selectAllTest(){
        itemMapper.selectAll().stream().map(itemVO -> itemVO.toString()).forEach(log::info);
    }
}
