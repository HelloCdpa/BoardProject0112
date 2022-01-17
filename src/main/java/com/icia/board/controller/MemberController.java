package com.icia.board.controller;

import com.icia.board.dto.MemberLoginDTO;
import com.icia.board.dto.MemberSaveDTO;
import com.icia.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;

    @GetMapping("/")
    public String saveForm(){
        return "/member/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO){
        Long saveId = ms.save(memberSaveDTO);
        return "/member/login";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session){

        if(ms.findByEmail(memberLoginDTO)){
            session.setAttribute("loginEmail",memberLoginDTO.getMemberEmail());

            return "/board/save";
        }else{
            return "/member/login";
        }

    }













}
