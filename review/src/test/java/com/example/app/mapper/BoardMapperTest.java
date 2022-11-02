package com.example.app.mapper;

import com.example.app.domain.vo.BoardVO;
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

//    목록
    @Test
    public void getListTest(){
        boardMapper.getList().stream().map(BoardVO::toString).forEach(log::info);
    }

//    추가
    @Test
    public void insertTest(){
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardTitle("테스트 제목22");
        boardVO.setBoardWriter("testB");
        boardVO.setBoardContent("테스트 내용22");
        boardMapper.insert(boardVO);
    }

//    수정
    @Test
    public void updateTest(){
        BoardVO boardVO = boardMapper.select(10L);
        Assertions.assertNotNull(boardVO);

        boardVO.setBoardTitle("수정한 제목");
        boardVO.setBoardContent("수정한 내용");
        log.info("UPDATE COUNT: " + boardMapper.update(boardVO));
    }

//    삭제
    @Test
    public void deleteTest(){
        Long boardNumber = 10L;
        BoardVO boardVO = boardMapper.select(boardNumber);
        Assertions.assertNotNull(boardVO);
        boardMapper.delete(boardNumber);

    }


}
