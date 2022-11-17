package com.example.myadvanced.repository;

import com.example.myadvanced.entity.Car;
import com.example.myadvanced.entity.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {
}
