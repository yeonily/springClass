package com.example.myintermediate.repository;

import com.example.myintermediate.entity.Computer;
import com.example.myintermediate.entity.Desktop;
import com.example.myintermediate.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesktopRepository extends JpaRepository<Desktop, Long> {
}
