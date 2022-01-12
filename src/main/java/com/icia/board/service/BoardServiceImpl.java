package com.icia.board.service;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;

    @Override
    public long save(BoardSaveDTO boardSaveDTO) {
        BoardEntity boardEntity = BoardEntity.saveBoard(boardSaveDTO);

        return br.save(boardEntity).getId();
    }

    @Override
    public List<BoardDetailDTO> findALl() {
        List<BoardEntity> boardEntities = br.findAll();
        List<BoardDetailDTO> board = new ArrayList<>();
        for(BoardEntity e : boardEntities ){
            board.add(BoardDetailDTO.toBoardDetail(e));
        }
        return board;
    }

    @Override
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        BoardEntity boardEntity = optionalBoardEntity.get();
        BoardDetailDTO board = BoardDetailDTO.toBoardDetail(boardEntity);
        return board;
    }
}
