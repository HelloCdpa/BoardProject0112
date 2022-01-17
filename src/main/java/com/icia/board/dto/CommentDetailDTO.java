package com.icia.board.dto;

import lombok.Data;

@Data
public class CommentDetailDTO {
    private Long boardId;
    private String commentWriter;
    private String commentContents;
}
