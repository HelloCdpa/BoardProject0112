package com.icia.board.service;

import com.icia.board.dto.MemberLoginDTO;
import com.icia.board.dto.MemberSaveDTO;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO);

    boolean findByEmail(MemberLoginDTO memberLoginDTO);


}
