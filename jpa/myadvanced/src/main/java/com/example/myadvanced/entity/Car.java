package com.example.myadvanced.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_CAR")
@Getter @Setter @ToString(exclude = "carOwner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Car extends Period{
    @Id @GeneratedValue
    private Long carId;
    private String carBrand;
    private String carName;
    private String carColor;
    private Long carPrice;
    private LocalDateTime carReleaseDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_OWNER_ID")
//    @ToString.Exclude
    private CarOwner carOwner;

    public void create(String carBrand, String carName, String carColor, Long carPrice, LocalDateTime carReleaseDate) {
        this.carBrand = carBrand;
        this.carName = carName;
        this.carColor = carColor;
        this.carPrice = carPrice;
        this.carReleaseDate = carReleaseDate;
    }

    public void changeCarOwner(CarOwner carOwner) {
        this.carOwner = carOwner;
    }
}
