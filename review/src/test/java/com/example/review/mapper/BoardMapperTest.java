package com.example.review.mapper;

import com.example.review.domain.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void getListTest(){
        boardMapper.getList().stream().map(BoardVO::toString).forEach(log::info);
    }

    @Test
    public void insertTest(){
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardTitle("테스트 제목1");
        boardVO.setBoardContent("테스트 내용1");
        boardVO.setBoardWriter("abc");
        boardMapper.insert(boardVO);
        log.info("추가된 게시글 번호 : " + boardVO.getBoardNumber());

    }

    @Test
    public void updateTest(){
        BoardVO boardVO = boardMapper.select(54l);
        Assertions.assertNotNull(boardVO);
        boardVO.setBoardTitle("ㅂㅇ");
        log.info("UPDATE COUNT : " + boardMapper.update(boardVO));
    }

    @Test
    public void deleteTest(){
        Long boardNumber = 54l;
        BoardVO boardVO = boardMapper.select(boardNumber);
        Assertions.assertNotNull(boardVO);
        boardMapper.delete(boardNumber);
    }
}
