package com.example.myintermediate.entity;

import com.example.myintermediate.type.KeyboardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("Desktop")
@Table(name = "TBL_DESKTOP")
@Getter @Setter @ToString
@NoArgsConstructor
public class Desktop extends Computer{
    @Enumerated(EnumType.STRING)
    @Column(name = "DESKTOP_KEYBOARD")
    private KeyboardType desktopKeyboard;

//    public void create(int computerScreen, String computerBrand, String computerName, int computerPrice, LocalDateTime computerReleaseDate, int computerRam, int computerSsd, int computerGpu, String computerProcessor, LocalDateTime computerCreatedDate, LocalDateTime computerUpdatedDate, KeyboardType desktopKeyboard) {
//        super.create( computerScreen,  computerBrand,  computerName,  computerPrice,  computerReleaseDate,  computerRam,  computerSsd,  computerGpu,  computerProcessor,  computerCreatedDate,  computerUpdatedDate);
//        this.desktopKeyboard = desktopKeyboard;
//    }



}
