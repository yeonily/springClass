package com.example.myadvanced.repository;

import com.example.myadvanced.entity.Owner;
import com.example.myadvanced.entity.OwnerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OwnerRepository extends JpaRepository<Owner, Long>, OwnerCustomRepository {
    @Query("select new com.example.myadvanced.entity.OwnerDTO(o.ownerId, o.ownerName, o.ownerPhone) from Owner o where o.ownerId = :ownerId")
    public OwnerDTO findByIdToDTO(Long ownerId);
}
