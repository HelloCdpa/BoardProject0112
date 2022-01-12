package com.icia.board;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardService bs;


    @Test
    @Transactional
    @Rollback
    @DisplayName("글작성 테스트")
    public void boardSaveTest(){
        String writer = "저장작성자1";
        String password = "저장비밀번호1";
        String title = "저장제목1";
        String content = "저장내용1";

        BoardSaveDTO boardSaveDTO = new BoardSaveDTO(writer,password,title,content);
        bs.save(boardSaveDTO);

    }








}
