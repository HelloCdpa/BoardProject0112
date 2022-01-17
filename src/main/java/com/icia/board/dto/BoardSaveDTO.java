package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveDTO {
//글쓰기 했을 때 받아 오는 값만
    private Long memberId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;

    public BoardSaveDTO(String boardWriter, String boardPassword, String boardTitle, String boardContents) {
        this.boardWriter = boardWriter;
        this.boardPassword = boardPassword;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
    }
}










