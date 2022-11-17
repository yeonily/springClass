package com.example.myadvanced.repository;

import com.example.myadvanced.entity.Owner;
import com.example.myadvanced.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
