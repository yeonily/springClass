package com.example.myintermediate.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

// 자바 진영에서는 상속관계이다. 하지만
// MappedSuperClass를 작성하여 RDB진영에는 상속관계가 아님을 표시해야 한다.
// 각 필드를 개별적으로 자주 사용하거나 바로 접근해야 할 때에는 지금처럼 @MappedSuperClass 방식을 사용한다.
@MappedSuperclass //자식클래스에 extends로 필드 공유 가능. 객체진영에서만 상속, rdb 상속 관계 아님. //모듈화
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class Period {//단독 사용 불가능, abstract 붙여줌.
    @Column(name = "COMPUTER_CREATED_DATE")
    @CreatedDate
    private LocalDateTime computerCreatedDate;
    @Column(name = "COMPUTER_UPDATED_DATE")
    @LastModifiedDate
    private LocalDateTime computerUpdatedDate;

//    순수 jpa방법
//    @PrePersist
//    public void created(){
//        this.computerCreatedDate = LocalDateTime.now();
//        this.computerUpdatedDate = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void updated(){
//        this.computerUpdatedDate = LocalDateTime.now();
//    }
}
