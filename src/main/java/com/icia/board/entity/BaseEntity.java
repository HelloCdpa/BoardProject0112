package com.icia.board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //자식들이 뭐하나 감지
@Getter
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false) //업데이트가 불가능하게 된 컬럼
    private LocalDateTime createTime; //inset 수행한 시간

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updateTime; //update 수행한 시간


}
