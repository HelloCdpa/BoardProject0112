package com.icia.board.dto;

import com.icia.board.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailDTO {
    Long memberId;
    String memberEmail;
    String memberPassword;
    String memberName;

public static MemberDetailDTO toMemberDetail(MemberEntity memberEntity){
    MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
    memberDetailDTO.setMemberId(memberEntity.getId());
    memberDetailDTO.setMemberName(memberEntity.getMemberName());
    memberDetailDTO.setMemberEmail(memberEntity.getMemberEmail());
    memberDetailDTO.setMemberPassword(memberEntity.getMemberPassword());
    return memberDetailDTO;

}







}
