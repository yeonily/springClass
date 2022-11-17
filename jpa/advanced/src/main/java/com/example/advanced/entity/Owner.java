package com.example.advanced.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_OWNER")
@Getter @Setter @ToString
// 무분별한 생성을 막기 위해서 public보다는 protected로 설정하고, Spring에서 관리되는 대상이므로 private은 적절하지 않다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner {
    @Id @GeneratedValue
    private Long ownerId;
    private String ownerName;
    @Column(unique = true)
    private String ownerPhone;
}
