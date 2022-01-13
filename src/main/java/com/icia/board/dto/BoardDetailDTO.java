package com.icia.board.dto;

import com.icia.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {
    private Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime CreateBoardDate;
    private LocalDateTime UpdateBoardDate;


    //detailDTO 로
    public static BoardDetailDTO toBoardDetail(BoardEntity boardEntity){
        BoardDetailDTO board = new BoardDetailDTO();

        board.setBoardId(boardEntity.getId());
        board.setBoardWriter(boardEntity.getBoardWriter());
        board.setBoardTitle(boardEntity.getBoardTitle());
        board.setBoardContents(boardEntity.getBoardContents());
        board.setBoardPassword(boardEntity.getBoardPassword());
        board.setCreateBoardDate(boardEntity.getCreateTime());
        board.setUpdateBoardDate(boardEntity.getUpdateTime());

        return board;

    }


}
