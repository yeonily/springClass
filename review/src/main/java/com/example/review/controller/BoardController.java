package com.example.review.controller;

import com.example.review.domain.vo.BoardVO;
import com.example.review.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService boardService;

    //  게시글 목록
    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute("boards", boardService.show());
    }
    //  게시글 등록
    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public void write(BoardVO boardVO){
        boardService.add(boardVO);
    }

    //  게시글 수정, 게시글 상세보기
    @GetMapping(value = {"read", "update"})
    public void read(Long boardNumber, Model model){
        model.addAttribute("board", boardService.find(boardNumber));
    }

    @PostMapping("/update")
    public String update(BoardVO boardVO, RedirectAttributes redirectAttributes){
        boardService.update(boardVO);
        redirectAttributes.addAttribute("boardNumber", boardVO.getBoardNumber());
        return "/board/read";
    }

    // 게시글 삭제
    @PostMapping("/delete")
    public String delete(Long boardNumber){
        boardService.delete(boardNumber);
        return "/board/list";
    }

}
