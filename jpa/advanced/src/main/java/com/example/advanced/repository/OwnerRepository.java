package com.example.advanced.repository;

import com.example.advanced.entity.Owner;
import com.example.advanced.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
