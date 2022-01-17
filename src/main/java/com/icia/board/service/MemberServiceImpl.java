package com.icia.board.service;

import com.icia.board.dto.MemberDetailDTO;
import com.icia.board.dto.MemberLoginDTO;
import com.icia.board.dto.MemberSaveDTO;
import com.icia.board.entity.MemberEntity;
import com.icia.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository mr;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberSave(memberSaveDTO);

        return mr.save(memberEntity).getId();
    }

    @Override
    public boolean findByEmail(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEmail = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        if (memberEmail != null) {
            if(memberEmail.getMemberPassword() == memberLoginDTO.getMemberPassword()){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }


}
