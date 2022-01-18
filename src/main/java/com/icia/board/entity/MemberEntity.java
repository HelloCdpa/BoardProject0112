package com.icia.board.entity;

import com.icia.board.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;
    //부모 지워지면 자식도 지워짐 on delete cascade
//    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
//    private List<CommentEntity> commentEntityList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
//    private List<BoardEntity> boardEntityList = new ArrayList<>();

    //on delete set null을 할 때 - preRemove() 도 같이 써야함
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.PERSIST,orphanRemoval = false,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.PERSIST,orphanRemoval = false,fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();



    public static MemberEntity toMemberSave(MemberSaveDTO MemberSaveDTO){
       MemberEntity memberEntity = new MemberEntity();
       memberEntity.setMemberEmail(MemberSaveDTO.getMemberEmail());
       memberEntity.setMemberPassword(MemberSaveDTO.getMemberPassword());
       memberEntity.setMemberName(MemberSaveDTO.getMemberName());
       return memberEntity;
    }
    // 부모를 지우는데 자식도 지우기 싫을 때
    @PreRemove
    private void preRemove(){
        System.out.println("MemberEntity.preRemove");
        boardEntityList.forEach(board -> board.setMemberEntity(null));
        //아래랑 위랑 같음
//        for(BoardEntity board:boardEntityList){
//            board.setMemberEntity(null);
//        }
        commentEntityList.forEach(comment ->comment.setMemberEntity(null));
    }





}
