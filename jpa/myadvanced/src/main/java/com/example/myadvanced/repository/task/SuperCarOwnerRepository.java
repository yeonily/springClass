package com.example.myadvanced.repository.task;

import com.example.myadvanced.entity.CarOwner;
import com.example.myadvanced.entity.task.SuperCarOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperCarOwnerRepository extends JpaRepository<SuperCarOwner, Long> {
}
