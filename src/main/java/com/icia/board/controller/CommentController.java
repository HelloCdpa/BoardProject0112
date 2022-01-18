package com.icia.board.controller;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.CommentDetailDTO;
import com.icia.board.dto.CommentSaveDTO;
import com.icia.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService cs;

    @PostMapping("/save")
    public @ResponseBody List<CommentDetailDTO> save (@ModelAttribute CommentSaveDTO commentSaveDTO, Model model){
        //저장
        Long commentId = cs.save(commentSaveDTO);
        //리스트로 전체 댓글 가져오기
        List<CommentDetailDTO> commentList = cs.findAll(commentSaveDTO.getBoardId());
        model.addAttribute("commentList",commentList);

        return commentList;
    }
}
