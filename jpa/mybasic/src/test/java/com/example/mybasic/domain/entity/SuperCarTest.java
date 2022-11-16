package com.example.mybasic.domain.entity;

import com.example.mybasic.repository.SuperCarDAO;
import com.example.mybasic.type.SuperCarBrand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SuperCarTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SuperCarDAO superCarDAO;

    @Test
    public void saveTest(){
        SuperCar bentley = new SuperCar();
        SuperCar lambo = new SuperCar();
        LocalDateTime bentleyReleaseDate = LocalDateTime.of(2019, 12, 4, 0, 0);
        LocalDateTime lamborghiniReleaseDate = LocalDateTime.of(2022, 4, 25, 0, 0);
//        bentleyReleaseDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

        bentley.create(SuperCarBrand.BENTLEY, "GT", "White", 350_000_000L, bentleyReleaseDate);
        lambo.create(SuperCarBrand.LAMBORGHINI, "Urus", "Yellow", 450_000_000L, lamborghiniReleaseDate);

        superCarDAO.save(bentley);
        superCarDAO.save(lambo);

//        for (int i=0; i<100; i++){
//            SuperCar superCar = new SuperCar();
//            LocalDateTime superCarReleaseDate = LocalDateTime.of(2019, 12, 4, 0, 0);
//            superCar.create(SuperCarBrand.BENTLEY, "White", "car" + (i + 1), 350_000_000L, superCarReleaseDate);
//            superCarDAO.save(superCar);
//        }

    }

    @Test
    public void findSuperCarBetweenReleaseDateTest(){
        LocalDateTime startDate = LocalDateTime.of(2019, 1, 5, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2021, 4, 25, 0, 0);
        assertThat(superCarDAO.findSuperCarBetweenReleaseDate(startDate, endDate).get(0).getSuperCarBrand()).isEqualTo(SuperCarBrand.BENTLEY);
    }

    @Test
    public void deleteTest(){
        superCarDAO.delete(superCarDAO.findById(2L).get());
    }

    @Test
    public void findByIdTest(){
        log.info("superCar: " + Optional.ofNullable(superCarDAO.findById(1L)));
    }


    @Test
    public void findAllTest(){
        superCarDAO.findAll().stream().map(SuperCar::toString).forEach(log::info);
    }

    @Test
    public void getCountOfSuperCar(){
        log.info("전체 차량 개수 조회: " + superCarDAO.getCountOfSuperCar());
    }

    @Test
    public void findSuperCarByReleaseDate(){
        LocalDateTime releaseDate = LocalDateTime.of(2022, 4, 25, 0, 0);
        String format = releaseDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        superCarDAO.findSuperCarByReleaseDate(format).stream().map(SuperCar::getSuperCarName).forEach(log::info);
    }

    @Test
    public void findSuperCarByColorContainingTest(){
//        superCarDAO.findSuperCarByColorContaining("e").stream().map(SuperCar::getSuperCarBrand).forEach(brand -> {
//            log.info("brand: " + brand);
//        });
        assertThat(superCarDAO.findSuperCarByColorContaining("e").stream().map(SuperCar::getSuperCarBrand).collect(Collectors.toList()).get(0).name()).isEqualTo("BENTLEY");
    }

    @Test
    public void findAllPagingTest(){
        superCarDAO.findAllPaging(0, 10).stream().map(SuperCar::getSuperCarName).forEach(log::info);
    }

    @Test
    public void bulkUpdateTest(){
        List<SuperCar> superCars = superCarDAO.findAllPaging(0, 10);
        superCarDAO.bulkUpdate(LocalDateTime.of(2022, 4, 25, 0, 0));

//        벌크 연산 수행 시 영속성 컨텍스트 모르게 SQL문이 싱핼되기 때문에 객체진영과 RDB진영 간의 데이터 불일치가 발생한다.
//        만약 동일한 트랜잭션에서 벌크연산을 수행하게 된다면, 반드시 벌크 연산 후 영속성 컨텍스트를 비워줘야 한다.
        entityManager.clear();
        superCars.stream().forEach(superCar -> {log.info(superCar.getSuperCarName() + "price: " + superCar.getSuperCarPrice());});

    }


//    @Test
//    public void bulkUpdateTest(){
////        List<SuperCar> superCars = superCarDAO.findAllPaging(0, 10);
//        String q1 = "select s from SuperCar s order by s.superCarId desc";
//        List<SuperCar> superCars = entityManager.createQuery(q1, SuperCar.class)
//                .setFirstResult(0) //0부터 시작
//                .setMaxResults(10)
//                .getResultList();
//
//        String superCarReleaseDate = LocalDateTime.of(2019, 12, 4, 0, 0).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
////        log.info("count: " + superCarDAO.bulkUpdate());
//
//        String q2 = "update SuperCar s set s.superCarPrice = s.superCarPrice * 1.1 where function('to_char', s.superCarReleaseDate, 'yyyyMMdd') = :superCarReleaseDate";
//        entityManager.createQuery(q2).setParameter("superCarReleaseDate", superCarReleaseDate).executeUpdate();
//
////        벌크 연산 수행 시 영속성 컨텍스트 모르게 SQL문이 실행되기 떄문에 객체진영과 RDB진영 간의 데이터 불일치가 발생한다.
////        만약 동일한 트랜잭션에서 벌크 연산을 수행하게 된다면, 반드시 벌크 연산 후 영속성 컨텍스트를 비워줘야 한다.
//        entityManager.clear();
//
//        superCars.stream().forEach(superCar -> {log.info(superCar.getSuperCarName() + " price: " + superCar.getSuperCarPrice());});
//
//    }

}
