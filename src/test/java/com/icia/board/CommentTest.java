package com.icia.board;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.CommentDetailDTO;
import com.icia.board.dto.CommentSaveDTO;
import com.icia.board.entity.CommentEntity;
import com.icia.board.repository.BoardRepository;
import com.icia.board.repository.CommentRepository;
import com.icia.board.service.BoardService;
import com.icia.board.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class CommentTest {
    @Autowired
    private BoardService bs;
    @Autowired
    private BoardRepository br;
    @Autowired
    private CommentService cs;
    @Autowired
    private CommentRepository cr;


    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("댓글작성 테스트")
    public void commentSaveTest(){
        //게시글 존재
        String writer = "작성자1";
        String password = "비밀번호1";
        String title = "제목1";
        String content = "내용1";
        BoardSaveDTO boardSaveDTO = new BoardSaveDTO(writer,password,title,content);
        long c_boardId = bs.save(boardSaveDTO);

        //댓글
        String c_writer = "댓글작성자";
        String c_contents = "댓글내용";
        CommentSaveDTO commentSaveDTO = new CommentSaveDTO(c_boardId,c_boardId,c_writer,c_contents);
        Long c_id = cs.save(commentSaveDTO);
    }
    @Test
    @Transactional // 다른 엔티티들도 조회할 때 트랜젝션을 꼭 써주기
    @DisplayName("댓글조회")
    public void findByIdTest(){
        CommentEntity commentEntity = cr.findById(1L).get();
        System.out.println("commentEntity.toString() = " + commentEntity.toString());
        System.out.println("commentEntity.getCommentWriter() = " + commentEntity.getMemberEntity());
        System.out.println("commentEntity.getCommentContents() = " + commentEntity.getCommentContents());
        System.out.println("commentEntity.getId() = " + commentEntity.getId());
        System.out.println("commentEntity.getBoardEntity() = " + commentEntity.getBoardEntity());
        System.out.println("commentEntity.getBoardEntity().getBoardTitle() = " + commentEntity.getBoardEntity().getBoardTitle());

        CommentEntity commentEntity2 = cr.findById(2L).get();
        System.out.println("commentEntity2.toString() = " + commentEntity2.toString());
        System.out.println("commentEntity2.getCommentWriter() = " + commentEntity2.getMemberEntity());
        System.out.println("commentEntity2.getCommentContents() = " + commentEntity2.getCommentContents());
        System.out.println("commentEntity2.getId() = " + commentEntity2.getId());
        System.out.println("commentEntity2.getBoardEntity() = " + commentEntity2.getBoardEntity());
        System.out.println("commentEntity2.getBoardEntity().getBoardTitle() = " + commentEntity2.getBoardEntity().getBoardTitle());
    }
    
    @Test
    @Transactional
    @DisplayName("댓글목록출력")
    public void findAllTest(){
        List<CommentDetailDTO> commentDetailDTOS = cs.findAll(2L);
        for (CommentDetailDTO c: commentDetailDTOS){
            System.out.println("c.toString() = " + c.toString());
        }
    }




}
