package com.example.app.mapper;

import com.example.app.domain.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
//   게시글 목록
    public List<BoardVO> getList();
}
