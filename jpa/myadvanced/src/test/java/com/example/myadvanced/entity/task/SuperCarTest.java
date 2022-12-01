package com.example.myadvanced.entity.task;

import com.example.myadvanced.embeddable.Address;
import com.example.myadvanced.entity.task.SuperCar;
import com.example.myadvanced.entity.task.SuperCarOwner;
import com.example.myadvanced.repository.task.SuperCarOwnerRepository;
import com.example.myadvanced.repository.task.SuperCarRepository;
import com.example.myadvanced.repository.task.SuperCarRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SuperCarTest {
    @Autowired private SuperCarRepository SuperCarRepository;
    @Autowired private SuperCarOwnerRepository SuperCarOwnerRepository;
    @Autowired private SuperCarRegistrationRepository SuperCarRegistrationRepository;

}
