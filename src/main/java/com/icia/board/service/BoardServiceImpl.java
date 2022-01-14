package com.icia.board.service;

import com.icia.board.common.PagingConst;
import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardPagingDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Long update(BoardUpdateDTO boardUpdateDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardUpdateDTO);


        return br.save(boardEntity).getId();
    }

    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        //페이징처리를 JPA 로 하는데 요구하는 파라미터를 잘 써야함
        //요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺸다.
//        page = page - 1;
        page = (page==1)? 0 : (page-1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        // Entity 필드이름 "id" order by board_id desc
      //Page<BoardEntity> => Page<BoardPagingDTO>
        Page<BoardPagingDTO> boardList = boardEntities.map(
                //board(엔티티 객체) : 엔티티 객체를 담기 위한 반복용 변수
                // Page<BoardEntity> -> Page<BoardPagingDTO>
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle())

        );

        return boardList;

    }
}
