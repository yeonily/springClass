package com.example.mybasic.domain.entity;


import com.example.mybasic.type.SuperCarBrand;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_SUPER_CAR")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 자식에서만 사용할 수 있게 막아둠.
public class SuperCar {
    @Id @GeneratedValue
    @Column(name = "SUPER_CAR_ID")
    private Long superCarId;
    @Enumerated(EnumType.STRING)
    @Column(name = "SUPER_CAR_BRAND")
    private SuperCarBrand superCarBrand;
    @Column(name = "SUPER_CAR_NAME")
    private String superCarName;
    @Column(name = "SUPER_CAR_COLOR")
    private String superCarColor;
    @Column(name = "SUPER_CAR_PRICE")
    private Long superCarPrice;
    @Column(name = "SUPER_CAR_RELEASE_DATE")
    private LocalDateTime superCarReleaseDate;

    public void create(SuperCarBrand superCarBrand, String superCarName, String superCarColor, Long superCarPrice, LocalDateTime superCarReleaseDate) {
        this.superCarBrand = superCarBrand;
        this.superCarName = superCarName;
        this.superCarColor = superCarColor;
        this.superCarPrice = superCarPrice;
        this.superCarReleaseDate = superCarReleaseDate;
    }
}
