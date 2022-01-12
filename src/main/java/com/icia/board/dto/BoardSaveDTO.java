package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveDTO {
//글쓰기 했을 때 받아 오는 값만
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;








}










