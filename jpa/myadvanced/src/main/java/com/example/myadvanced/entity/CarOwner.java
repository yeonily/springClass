package com.example.myadvanced.entity;

import com.example.myadvanced.embeddable.Address;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_CAR_OWNER")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarOwner extends Period{
    @Id @GeneratedValue
    private Long carOwnerId;
    private String carOwnerName;
    private int carOwnerAge;
    @Embedded
    private Address address;

    public void create(String carOwnerName, int carOwnerAge, Address address) {
        this.carOwnerName = carOwnerName;
        this.carOwnerAge = carOwnerAge;
        this.address = address;
    }
}
