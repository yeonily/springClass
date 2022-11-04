package com.example.review.service;

import com.example.review.domain.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Test
    public void showTest(){
        boardService.show().stream().map(BoardVO::toString).forEach(log::info);
    }

    @Test
    public void addTest(){
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardTitle("11");
        boardVO.setBoardContent("11");
        boardVO.setBoardWriter("ccc");
        boardService.add(boardVO);
        log.info("추가된 게시글 번호 : " + boardVO.getBoardNumber());

    }

    @Test
    public void updateTest(){
        BoardVO boardVO = boardService.find(54l);
        Assertions.assertNotNull(boardVO);
        boardVO.setBoardTitle("ㅂㅇ");
        log.info("UPDATE : " + boardService.update(boardVO));
    }

    @Test
    public void deleteTest(){
        Long boardNumber = 54l;
        BoardVO boardVO = boardService.find(boardNumber);
        Assertions.assertNotNull(boardVO);
        boardService.delete(boardNumber);
    }
}
