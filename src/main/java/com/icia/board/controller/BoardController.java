package com.icia.board.controller;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService bs;
    @GetMapping("save")
    public String saveForm(){
        return "/board/save";
    }
    @PostMapping("save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO){

        bs.save(boardSaveDTO);


        return "redirect:/board/";
    }

    @GetMapping
    public String findAll(Model model){
        List<BoardDetailDTO> boardList = bs.findALl();
        model.addAttribute("boardList",boardList);
        return "/board/findAll";
    }

    @GetMapping("{boardId}")
    public String detail(@PathVariable ("boardId") Long boardId, Model model){
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "/board/detail";
    }




}
