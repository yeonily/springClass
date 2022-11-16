package com.example.myintermediate.repository;

import com.example.myintermediate.entity.Desktop;
import com.example.myintermediate.entity.Phone;
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
        Phone phone = new Phone();

        desktop.create(1024, "samsung","mycom", 2_000_000, LocalDateTime.of(2019,10,21,0,0), 8, 100, 16, "Window", LocalDateTime.now(), LocalDateTime.of(2022,12,12,0,0), KeyboardType.KOREAN);
        phone.create(1024, "galaxy","myphone", 1_000_000, LocalDateTime.of(2021,10,21,0,0), 8, 100, 16, "Android", LocalDateTime.now(), LocalDateTime.of(2022,12,12,0,0), 100);

        assertThat(desktopRepository.save(desktop).getComputerBrand()).isEqualTo("samsung");
        assertThat(phoneRepository.save(phone).getComputerBrand()).isEqualTo("galaxy");
    }
}
