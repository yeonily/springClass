package com.example.myintermediate.repository;

import com.example.myintermediate.entity.Desktop;
import com.example.myintermediate.entity.Phone;
import com.example.myintermediate.type.Hardware;
import com.example.myintermediate.type.KeyboardType;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ComputerRepositoryTest {
    @Autowired private DesktopRepository desktopRepository;
    @Autowired private PhoneRepository phoneRepository;

    @Test
    public void saveTest(){
        Desktop desktop = new Desktop();
        Hardware hardware = new Hardware();

        hardware.create(8, 512, "RTX3050", "Intel");

        desktop.create(1980, "삼성","갤럭시북", 2_000_000, LocalDateTime.of(2019,10,21,0,0), hardware, KeyboardType.KOREAN);

        desktopRepository.save(desktop);
    }

    @Test
    public void updateTest(){
//        assertThat(desktopRepository.findById(1l).get().getComputerBrand()).isEqualTo("삼성"); //검증완
        desktopRepository.findById(1l).get().setComputerBrand("LG");
    }
}
