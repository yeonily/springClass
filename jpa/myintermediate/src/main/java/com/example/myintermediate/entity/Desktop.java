package com.example.myintermediate.entity;

import com.example.myintermediate.type.Hardware;
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

    public void create(int computerScreen, String computerBrand, String computerName, int computerPrice, LocalDateTime computerReleaseDate, Hardware hardware,  KeyboardType desktopKeyboard) {
        super.create(computerScreen, computerBrand, computerName, computerPrice, computerReleaseDate, hardware);
        this.desktopKeyboard = desktopKeyboard;
    }
}
