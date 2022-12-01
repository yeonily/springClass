package com.example.myadvanced.repository.task;

import com.example.myadvanced.entity.task.SuperCar;
import com.example.myadvanced.entity.task.SuperCarOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperCarRepository extends JpaRepository<SuperCar, Long> {
}
