package com.example.myadvanced.repository.task;

import com.example.myadvanced.entity.task.SuperCar;
import com.example.myadvanced.entity.task.SuperCarRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperCarRegistrationRepository extends JpaRepository<SuperCarRegistration, Long> {
}
