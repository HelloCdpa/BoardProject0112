package com.icia.board;


import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import com.icia.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    @DisplayName("테스트 글 저장")
    public void boardTestSave() {
//        for (int i = 1; i < 16; i++) {
//            BoardSaveDTO boardSaveDTO = new BoardSaveDTO("writer" + i, "password" + i, "title" + i, "content" + i);
//        }
//  IntStream.rangeClosed 1~30 포함 IntStream.range 1~30 람다식
        IntStream.rangeClosed(1,30).forEach(i->{
            BoardSaveDTO boardSaveDTO = new BoardSaveDTO("writer" + i, "password" + i, "title" + i, "content" + i);
            bs.save(boardSaveDTO);
        });


    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("전체조회 테스트")
    public void boardFindAllTest(){
        int size = bs.findALl().size();
        assertThat (size).isEqualTo(30);
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("상세조회 테스트")
    public void boardDetailTest(){
        String writer = "저장작성자1";
        String password = "저장비밀번호1";
        String title = "저장제목1";
        String content = "저장내용1";

        BoardSaveDTO boardSaveDTO = new BoardSaveDTO(writer,password,title,content);
        long boardId = bs.save(boardSaveDTO);

        BoardDetailDTO board = bs.findById(boardId);
        assertThat(board.getBoardId()).isEqualTo(boardId);

    }
    @Test
    @Transactional
    @Rollback
    @DisplayName("글삭제 테스트")
    public void boardDeleteTest(){
        String writer = "삭제작성자1";
        String password = "삭제비밀번호1";
        String title = "삭제제목1";
        String content = "삭제내용1";

        BoardSaveDTO boardSaveDTO = new BoardSaveDTO(writer,password,title,content);
        long boardId = bs.save(boardSaveDTO);

        bs.deleteById(boardId);
        assertThrows(NoSuchElementException.class, ()->{
            assertThat(bs.findById(boardId)).isNull();
        });

    }
    @Test
    @Transactional
    @Rollback
    @DisplayName("글업데이트 테스트")
    public void boardUpdateTest() {
        String writer = "작성자1";
        String password = "비밀번호1";
        String title = "제목1";
        String content = "내용1";

        String updateTitle = "글업데이트 제목1";
        String updateContent = "글업데이트 내용1";

        BoardSaveDTO boardSaveDTO = new BoardSaveDTO(writer, password, title, content);
        long boardId = bs.save(boardSaveDTO);
        BoardDetailDTO saveDTO = bs.findById(boardId);

        BoardUpdateDTO boardUpdateDTO = new BoardUpdateDTO(boardId,writer,password,updateTitle,updateContent);

        long updateId = bs.update(boardUpdateDTO);
        BoardDetailDTO updateDTO = bs.findById(updateId);


        assertThat(saveDTO.getBoardTitle()).isNotEqualTo(updateDTO.getBoardTitle());

    }











}
