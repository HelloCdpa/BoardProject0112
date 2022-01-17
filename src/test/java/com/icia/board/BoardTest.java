package com.icia.board;


import com.icia.board.common.PagingConst;
import com.icia.board.dto.*;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import com.icia.board.service.BoardService;
import com.icia.board.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
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
    @Autowired
    private BoardRepository br;
    @Autowired
    private CommentService cs;

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

    @Test
    @DisplayName("삼항연산자")
    public void test1(){
        int num = 10;
        int num1 = 0;
        if(num == 10) {
            num1 = 5;
        }else {
            num1 = 100;
        }

        num1 = (num==10)? 5 : 100;
        // 조건 ? t참일 때 : f 거짓일 때 좌변에 대입시킴.
    }

    @Test
    @Transactional
    @DisplayName("페이징테스트")
    public void pagingTest(){
        int page = 5;
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        //page 객체가 제공해 주는 메서드 확인
        System.out.println("boardEntities.getContent() = "+boardEntities.getContent()); //요청페이지에 들어있는 데이터
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); //전체 글 갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); //요청페이지 JPA 기준
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); //한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫페이지인지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막페이지인지 여부

        //map() : 엔티티가 담긴 페이지 객체를 dto 가 담긴 페이지 객체로 변환해주는 역할
        Page<BoardPagingDTO> boardList = boardEntities.map(
                //board(엔티티 객체) : 엔티티 객체를 담기 위한 반복용 변수
                // Page<BoardEntity> -> Page<BoardPagingDTO>
                board -> new BoardPagingDTO(board.getId(),
                                            board.getBoardWriter(),
                                            board.getBoardTitle())

        );
        System.out.println("boardList.getContent() = "+boardList.getContent()); //요청페이지에 들어있는 데이터
        System.out.println("boardList.getTotalElements() = " + boardList.getTotalElements()); //전체 글 갯수
        System.out.println("boardList.getNumber() = " + boardList.getNumber()); //요청페이지 JPA 기준
        System.out.println("boardList.getTotalPages() = " + boardList.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardList.getSize() = " + boardList.getSize()); //한 페이지에 보여지는 글 갯수
        System.out.println("boardList.hasPrevious() = " + boardList.hasPrevious()); // 이전 페이지 존재여부
        System.out.println("boardList.isFirst() = " + boardList.isFirst()); // 첫페이지인지 여부
        System.out.println("boardList.isLast() = " + boardList.isLast()); // 마지막페이지인지 여부



    }

    @Test
    @Transactional
    @Commit
    @DisplayName("댓글작성 테스트")
    public void commentSaveTest(){
        String writer = "작성자1";
        String password = "비밀번호1";
        String title = "제목1";
        String content = "내용1";
        BoardSaveDTO boardSaveDTO = new BoardSaveDTO(writer,password,title,content);
        long c_boardId = bs.save(boardSaveDTO);

        String c_writer = "댓글작성자";
        String c_contents = "댓글내용";

        CommentSaveDTO commentSaveDTO = new CommentSaveDTO(c_boardId,c_writer,c_contents);
        Long c_id = cs.save(commentSaveDTO);

        System.out.println(c_id);





    }












}
