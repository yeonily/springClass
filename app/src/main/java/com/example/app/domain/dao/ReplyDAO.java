package com.example.app.domain.dao;

import com.example.app.domain.vo.ReplyVO;
import com.example.app.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {

    private final ReplyMapper replyMapper;

    //    추가
    public void insert(ReplyVO replyVO){
        replyMapper.insert(replyVO);
    }
    //    목록
    public List<ReplyVO> selectAll(Long boardNumber){
        return replyMapper.selectAll(boardNumber);
    }
    //    수정
    public void update(ReplyVO replyVO){
        replyMapper.update(replyVO);
    }
    //    삭제
    public void delete(Long replyNumber){
        replyMapper.delete(replyNumber);
    }
    //    전체 개수(게시글 하나당)
    public int selectCountOfReply(Long boardNumber){
        return replyMapper.selectCountOfReply(boardNumber);
    }
}
