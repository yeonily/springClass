package com.example.myadvanced.entity;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "TBL_OWNER")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//무분별한 생성을 막기 위해서 public보다는 protected로 설정하고, Spring에서 관리하는 대상이므로 private은 적절하지 않다.
public class Owner {
    @Id @GeneratedValue
    private Long ownerId;
    private String ownerName;
    @Column(unique = true)
    private String ownerPhone;
}
