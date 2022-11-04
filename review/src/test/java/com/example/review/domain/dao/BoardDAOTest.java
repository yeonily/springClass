package com.example.review.domain.dao;

import com.example.review.domain.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardDAOTest {
    @Autowired
    private BoardDAO boardDAO;


    @Test
    public void findAllTest(){
        boardDAO.findAll().stream().map(BoardVO::toString).forEach(log::info);
    }

    @Test
    public void saveTest(){
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardTitle("11");
        boardVO.setBoardContent("11");
        boardVO.setBoardWriter("ccc");
        boardDAO.save(boardVO);
        log.info("추가된 게시글 번호 : " + boardVO.getBoardNumber());

    }

    @Test
    public void setBoardTest(){
        BoardVO boardVO = boardDAO.findById(50l);
        Assertions.assertNotNull(boardVO);
        boardVO.setBoardTitle("ㅂㅇ");
        log.info("UPDATE COUNT : " + boardDAO.setBoard(boardVO));
    }

    @Test
    public void deleteByIdTest(){
        Long boardNumber = 50l;
        BoardVO boardVO = boardDAO.findById(boardNumber);
        Assertions.assertNotNull(boardVO);
        boardDAO.deleteById(boardNumber);
    }
}
