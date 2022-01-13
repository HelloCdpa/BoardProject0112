package com.icia.board.controller;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j // 로그기록 보기
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
 // 서비스 메서드를 사용하려면 의존성을 주입해야 하는데 필드@Auto, 생성자@ReCon 주입해야 한다.
    private final BoardService bs;
    @GetMapping("/save")
    public String saveForm(){
        return "/board/save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO){

        bs.save(boardSaveDTO);


        return "redirect:/board/";
    }

    @GetMapping
    public String findAll(Model model){
        log.info("findALl 호출");
        List<BoardDetailDTO> boardList = bs.findALl();
        model.addAttribute("boardList",boardList);
        return "/board/findAll";
    }

    @GetMapping("/{boardId}")
    public String findById(@PathVariable Long boardId, Model model){
        log.info("글보기 메서드 호출. 요청 글번호 : {}", boardId);
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "board/findById";
    }

    @PostMapping ("/{boardId}")
    public ResponseEntity findById2(@PathVariable Long boardId){
        log.info("글보기 메서드 호출. 요청 글번호 : {}", boardId);
        BoardDetailDTO board = bs.findById(boardId);

        return new ResponseEntity<BoardDetailDTO>(board,HttpStatus.OK);
    }


    @GetMapping ("/delete/{boardId}")
    public String deleteById(@PathVariable ("boardId")Long boardId){
        bs.deleteById(boardId);
        return "redirect:/board/";
    }
    @DeleteMapping ("/{boardId}")
    public ResponseEntity deleteById2(@PathVariable ("boardId")Long boardId){
        bs.deleteById(boardId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable ("boardId") Long boardId,Model model){
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "/board/update";

    }
    @PostMapping("/update")
    public String update1(@ModelAttribute BoardDetailDTO boardDetailDTO){


        bs.update(boardDetailDTO);

        return "redirect:/board/";
    }

    @PutMapping("/{boardId}")
    public ResponseEntity update2(@RequestBody BoardDetailDTO boardDetailDTO){
        Long Id = bs.update(boardDetailDTO);
        return new ResponseEntity(HttpStatus.OK);

    }







}
