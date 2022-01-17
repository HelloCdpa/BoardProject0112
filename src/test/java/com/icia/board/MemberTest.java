package com.icia.board;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.CommentSaveDTO;
import com.icia.board.dto.MemberSaveDTO;
import com.icia.board.repository.BoardRepository;
import com.icia.board.service.BoardService;
import com.icia.board.service.CommentService;
import com.icia.board.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
public class MemberTest {
    @Autowired
    private BoardService bs;
    @Autowired
    private BoardRepository br;
    @Autowired
    private MemberService ms;
    @Autowired
    private CommentService cs;


    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("회원 게시글 작성")
    public void memberBoardSave(){

        String memberEmail = "작성자2";
        String memberPassword = "비밀번호2";
        String memberName = "제목2";

        MemberSaveDTO memberSaveDTO = new MemberSaveDTO(memberName,memberEmail,memberPassword);
        Long memberId = ms.save(memberSaveDTO);

        String password = "글비밀번호2";
        String title = "제목2";
        String content = "내용2";

        BoardSaveDTO boardSaveDTO = new BoardSaveDTO(memberId,memberSaveDTO.getMemberName(),password,title,content);
        long boardId = bs.save(boardSaveDTO);
        System.out.println("boardSaveDTO = " + boardSaveDTO);


        String c_title = "제목2";
        String c_content = "내용2";
        CommentSaveDTO commentSaveDTO = new CommentSaveDTO(boardId,memberId,c_title,c_content);
        cs.save(commentSaveDTO);


    }

}
