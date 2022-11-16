package com.example.myintermediate.repository;

import com.example.myintermediate.entity.Computer;
import com.example.myintermediate.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
