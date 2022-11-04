package com.example.review.domain.dao;

import com.example.review.domain.vo.BoardVO;
import com.example.review.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardMapper boardMapper;

//    게시글 목록
    public List<BoardVO> findAll(){
        return boardMapper.getList();
    }
//    게시글 조회
    public BoardVO findById(Long boardNumber){
        return boardMapper.select(boardNumber);
    }
//    게시글 추가
    public int save(BoardVO boardVO){
        return boardMapper.insert(boardVO);
    }
//    게시글 수정
    public int setBoard(BoardVO boardVO){
        return boardMapper.update(boardVO);
    }
//    게시글 삭제
    public int deleteById(Long boardNumber){
        return boardMapper.delete(boardNumber);
    }

}
