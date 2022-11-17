package com.example.myadvanced.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class Period {
    @Column(name = "CAR_CREATED_DATE")
    @CreatedDate
    private LocalDateTime carCreatedDate;
    @Column(name = "CAR_UPDATED_DATE")
    @LastModifiedDate
    private LocalDateTime carUpdatedDate;
}
