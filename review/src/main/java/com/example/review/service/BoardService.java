package com.example.review.service;

import com.example.review.domain.vo.BoardVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    //    게시글 목록
    public List<BoardVO> show();
    //    게시글 조회
    public BoardVO find(Long boardNumber);
    //    게시글 추가
    public boolean add(BoardVO boardVO);
    //    게시글 수정
    public boolean update(BoardVO boardVO);
    //    게시글 삭제
    public boolean delete(Long boardNumber);
}
