package com.example.myadvanced.repository;

import com.example.myadvanced.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
