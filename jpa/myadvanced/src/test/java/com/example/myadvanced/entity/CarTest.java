package com.example.myadvanced.entity;

import com.example.myadvanced.embeddable.Address;
import com.example.myadvanced.repository.CarOwnerRepository;
import com.example.myadvanced.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CarTest {
    @Autowired private CarRepository carRepository;
    @Autowired private CarOwnerRepository carOwnerRepository;

    @Test
    public void saveTest(){
        Car car1 = new Car();
        Car car2 = new Car();

        CarOwner carOwner = new CarOwner();
        Address address = new Address();
        address.create("08804", "서울시", "그린빌");

        carOwner.create("김지연", 20, address);

        carOwnerRepository.save(carOwner);

        car1.create("sonata", "nata", "white", 20_000_000l, LocalDateTime.now());
        car2.create("sonata-lpg", "nata", "black", 10_000_000l, LocalDateTime.now());

        carRepository.save(car1);
        carRepository.save(car2);
    }

    @Test
    public void findTest(){
        Optional<Car> findCar = carRepository.findById(2l);
        if(findCar.isPresent()){
            Assertions.assertThat(findCar.get().getCarColor()).isEqualTo("white");
            log.info("owner address: " + findCar.get().getCarOwner().getAddress().getCarOwnerAddress());
            log.info("owner: " + findCar.get().getCarOwner().getClass());
        }
    }

    @Test
    public void updateTest(){
        carRepository.findAll().get(0).getCarOwner().setCarOwnerName("Lazy Kim");
    }

}
