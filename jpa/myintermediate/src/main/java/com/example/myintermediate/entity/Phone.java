package com.example.myintermediate.entity;

import com.example.myintermediate.type.Hardware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("Phone")
@Table(name = "TBL_PHONE")
@Getter @Setter @ToString
@NoArgsConstructor
public class Phone extends Computer{
    @Column(name = "PHONE_BATTERY")
    private int phoneBattery;

    public void create(int computerScreen, String computerBrand, String computerName, int computerPrice, LocalDateTime computerReleaseDate, Hardware hardware, LocalDateTime computerCreatedDate, LocalDateTime computerUpdatedDate, int phoneBattery) {
        super.create(computerScreen, computerBrand, computerName, computerPrice, computerReleaseDate, hardware);
        this.phoneBattery = phoneBattery;
    }
}
