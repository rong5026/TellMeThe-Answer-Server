package com.capstone.answer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 엔티티 클래스의 생명주기 이벤트를 감지함 -> 발생했을 때 특정 리스너 클래스를 실행하도록 지정하는데 사용됨
// 따라서 BaseTimeEntity를 상속받는 클래스의 생명주기 이벤트가 발생할 때마다 MyEntityListener 클래스의 메서드가 호출
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseTimeEntity {

    //생성일자는 수정하면 안되니까 update = false
    @CreatedDate
    @Column(updatable=false)
    private LocalDateTime createDate;

    // 조회한 Entity 값을 변경할 때 시간이 자동 저장
    @LastModifiedDate
    @Column(updatable=true)
    private LocalDateTime lastModifiedDate;

}
