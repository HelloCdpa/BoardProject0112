package com.icia.board.service;

import com.icia.board.dto.CommentSaveDTO;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);
}
