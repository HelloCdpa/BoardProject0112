package com.icia.board.service;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;

import java.util.List;

public interface BoardService {
    long save(BoardSaveDTO boardSaveDTO);

    List<BoardDetailDTO> findALl();

    BoardDetailDTO findById(Long boardId);

    void deleteById(Long boardId);

    public Long update(BoardUpdateDTO boardUpdateDTO);
}
