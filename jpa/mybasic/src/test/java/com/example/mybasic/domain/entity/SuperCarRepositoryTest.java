package com.example.mybasic.domain.entity;

import com.example.mybasic.repository.SuperCarRepository;
import com.example.mybasic.type.SuperCarBrand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)//단위테스트에서는 자동 롤백이 됨.
public class SuperCarRepositoryTest {

    @Autowired
    private SuperCarRepository superCarRepository;

    @Test
    public void saveTest(){
        SuperCar superCar1 = new SuperCar();
        SuperCar superCar2 = new SuperCar();

        LocalDateTime superCarReleaseDate1 = LocalDateTime.of(2021, 12, 4, 0, 0);
        LocalDateTime superCarReleaseDate2 = LocalDateTime.of(2022, 12, 14, 0, 0);
        superCar1.create(SuperCarBrand.BENTLEY, "car3", "Yellow",  300_000_000L, superCarReleaseDate1);
        superCar2.create(SuperCarBrand.FERRARI, "car4", "Blue",  400_000_000L, superCarReleaseDate2);

        superCarRepository.save(superCar1);
        superCarRepository.save(superCar2);
    }

    @Test
    public void findByIdTest(){
        assertThat(superCarRepository.findAll().stream().map(SuperCar::getSuperCarName).collect(Collectors.toList()).get(0)).isEqualTo("car1");
    }

//    @Test
//    public void updateTest(){
//        superCarRepository.findById(5l).get().setSuperCarColor("black");
//    }

    @Test
    public void deleteTest(){
        superCarRepository.deleteById(5l);
    }

    @Test
    public void countTest(){
        log.info("total: " + superCarRepository.count());
    }

    @Test
    public void findBySuperCarReleaseDateTest(){
        LocalDateTime releaseDate = LocalDateTime.of(2021, 12, 4, 0, 0);
        superCarRepository.findBySuperCarReleaseDate(releaseDate).stream().map(SuperCar::getSuperCarName).forEach(log::info);
        superCarRepository.findBySuperCarReleaseDate(LocalDateTime.of(2019,12,4,0,0)).stream().map(SuperCar::toString).forEach(log::info);
    }

    @Test
    public void findBySuperCarColorContainingTest(){
        superCarRepository.findBySuperCarColorContaining("l").stream().map(SuperCar::getSuperCarName).forEach(log::info);
    }

    @Test
    public void findBySuperCarReleaseDateBetween(){
        LocalDateTime start = LocalDateTime.of(2020, 12, 4, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 12, 4, 0, 0);
        superCarRepository.findBySuperCarReleaseDateBetween(start, end).stream().map(SuperCar::toString).forEach(log::info);
    }


    @Test
    public void findAll(){
//        page는 0부터 시작한다.
        superCarRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "superCarId")))
                .getContent().stream().map(SuperCar::toString).forEach(log::info);
    }

    @Test
    public void findTop10ByOrderBySuperCarIdDescTest(){
        superCarRepository.findTop10ByOrderBySuperCarIdDesc().stream().map(SuperCar::toString).forEach(log::info);
    }


    @Test
    public void updateTest(){
        log.info("count: " + superCarRepository.update(LocalDateTime.of(2019,12,4,0,0)));
    }
}
