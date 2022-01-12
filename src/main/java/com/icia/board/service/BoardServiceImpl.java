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
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardSaveDTO);

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
        //널이든 아니든 Optional 로 일단 감쌌다
        //널이 아니라면 값을 꺼내오고 널이면 다르게 처리하려고
        //프로그램이 중단되는 것을 막기위해서
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        BoardDetailDTO board = null;
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            board = BoardDetailDTO.toBoardDetail(boardEntity);
        }


        return board;
    }

    @Override
    public void deleteById(Long boardId) {
         br.deleteById(boardId);
    }

    @Override
    public Long update(BoardDetailDTO boardDetailDTO) {
        BoardEntity save = br.save(BoardEntity.toUpdateEntity(boardDetailDTO));
        Long boardId = save.getId();
        return boardId;
    }
}
