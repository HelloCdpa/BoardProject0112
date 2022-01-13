package com.icia.board.entity;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_table")

public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id; //값을 안 넣었을 때 null 포인트 방지
    // 작성자, 비밀번호, 제목, 내용
    @Column(length = 20)
    private String boardWriter;
    @Column(length = 20)
    private String boardPassword;
    @Column(length = 100)
    private String boardTitle;
    @Column(length = 1000)
    private String boardContents;
//    @Column
//    private LocalDateTime boardDate;

    //entity 로
    public static BoardEntity toSaveEntity(BoardSaveDTO boardSaveDTO) {
        BoardEntity board = new BoardEntity();
        board.setBoardWriter(boardSaveDTO.getBoardWriter());
        board.setBoardTitle(boardSaveDTO.getBoardTitle());
        board.setBoardContents(boardSaveDTO.getBoardContents());
        board.setBoardPassword(boardSaveDTO.getBoardPassword());
//        board.setBoardDate(LocalDateTime.now());

        return board;
    }

        public static BoardEntity toUpdateEntity(BoardUpdateDTO boardUpdateDTO){
            BoardEntity board = new BoardEntity();
            board.setId(boardUpdateDTO.getBoardId());
            board.setBoardWriter(boardUpdateDTO.getBoardWriter());
            board.setBoardTitle(boardUpdateDTO.getBoardTitle());
            board.setBoardContents(boardUpdateDTO.getBoardContents());
            board.setBoardPassword(boardUpdateDTO.getBoardPassword());
//            board.setBoardDate(LocalDateTime.now());

            return board;


    }





}
