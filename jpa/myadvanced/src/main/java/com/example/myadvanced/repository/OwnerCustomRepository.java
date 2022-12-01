package com.example.myadvanced.repository;

import com.example.myadvanced.entity.Owner;
import com.example.myadvanced.entity.OwnerDTO;
import com.example.myadvanced.entity.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerCustomRepository {
    public List<OwnerDTO> findAllSearch(Search search);



    public Page<Owner> findAllByOwnerName(Pageable pageable);
    public List<OwnerDTO> findAllByOwnerName(String ownerName);
}
