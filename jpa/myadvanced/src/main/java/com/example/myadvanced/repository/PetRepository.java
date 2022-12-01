package com.example.myadvanced.repository;

import com.example.myadvanced.entity.Pet;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    public List<Pet> findByPetName(@Param("petName") String petName);

    //        순수jpq, jpql방식
    @Query("select p from Pet p join fetch p.owner") //fetch를 쓰면 알아서 pet과 owner가 join됨.
    public List<Pet> findAllFetchJoin();

    //      spring data jpa방식
    @Override //원래 findAll메소드도 있으니까 @override 걸어주기
    @EntityGraph(attributePaths = "owner") //owner랑 패치조인
//    @EntityGraph(attributePaths = {"owner","",""}) // 여러 개면 중괄호 사용
    public List<Pet> findAll();

    @EntityGraph(attributePaths = "owner")
    @Query("select p from Pet p where p.petName = :petName")
    public List<Pet> findAllWithOwner(String petName);//메소드 이름 내가 지을 거면, @쿼리 써주기.
}
