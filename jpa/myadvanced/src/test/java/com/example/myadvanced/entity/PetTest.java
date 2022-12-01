package com.example.myadvanced.entity;

import com.example.myadvanced.repository.OwnerRepository;
import com.example.myadvanced.repository.PetRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.myadvanced.entity.QOwner.owner;
import static com.example.myadvanced.entity.QPet.pet;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class PetTest {
    @Autowired private PetRepository petRepository;
    @Autowired private OwnerRepository ownerRepository;
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Test
    public void queryDslTest(){
//        for(int i=0 ; i<100 ; i++){
//            Owner owner = new Owner();
//            owner.setOwnerName("홍길동");
//            owner.setOwnerPhone(i + 1 + "");
//            ownerRepository.save(owner);
//        }//100번 저장

        //화면에서 받는 매개변수로 생각하기(파라미터로 받기)
        Pageable pageable = PageRequest.of(1, 10);

        List<Owner> owners = jpaQueryFactory.selectFrom(owner)
                .where(owner.ownerName.eq("홍길동"))
                .orderBy(owner.ownerId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(owner)
                .where(owner.ownerName.eq("홍길동"))
                .fetch().size();//총 개수

        //PageImpl : 페이징 처리하는 spring-data-jpa를 구현해놓은 구현체, 페이지를 구현한 애(Pageable)
        //pageable 화면에서 전달받아야 하는건데, 단위테스트이다 보니, 직접 작성
        Page<Owner> ownerPaging = new PageImpl<>(owners, pageable, total);//페이지 타입으로 변경
        ownerPaging.getTotalPages();//전체 페이지 개수
        ownerPaging.getContent();//1페이지에 있는 ownerlist
        ownerPaging.getNumber();//현재 페이지

        ownerPaging.isFirst();//현재 페이지가 첫 페이지인지?
        ownerPaging.isLast();//realEnd 구분

        ownerPaging.getContent().stream().map(Owner::toString).forEach(log::info); //1페이지에 있는 onwerlist 출력

    }


    @Test
    public void queryDslTest2() {
        jpaQueryFactory.select(owner.count()) //집계함수도 사용 가능 (max, avg, sum,...)
                .from(owner)
                .fetch(); //결과가 하나
    }

    @Test
    public void queryDslTest3(){
        List<Pet> fetchjoin = jpaQueryFactory.select(pet)
                .from(pet).join(pet.owner).fetchJoin() //fetchjoin할 때 연관관계의 주인을 써줘야 함.
                .fetch();

    }

    @Test
    public void queryDslTest4(){
        Search search = new Search();
        search.setName("홍길동");
//        search.setAge(20); 조건에 맞게 적용됨.
        assertThat(ownerRepository.findAllSearch(search).size()).isEqualTo(50);

    }



    @Test
    public void saveTest(){
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
        Pet pet3 = new Pet();
        Pet pet4 = new Pet();

        Owner owner1 = new Owner();
        Owner owner2 = new Owner();

        owner1.setOwnerName("한동석");
        owner1.setOwnerPhone("01012341234");
        owner2.setOwnerName("홍길동");
        owner2.setOwnerPhone("01012345555");

        ownerRepository.save(owner1);
        ownerRepository.save(owner2);

        pet1.setPetName("Tom");
        pet1.setPetGender("Male");
        pet1.setPetDisease("Cold");
        pet1.setOwner(owner1);

        pet2.setPetName("Jack");
        pet2.setPetGender("Female");
        pet2.setPetDisease("Fracture");
        pet2.setOwner(owner1);

        petRepository.save(pet1);
        petRepository.save(pet2);

        pet3.setPetName("Bell");
        pet3.setPetGender("Male");
        pet3.setPetDisease("Cold");
        pet3.setOwner(owner2);

        pet4.setPetName("Lisa");
        pet4.setPetGender("Female");
        pet4.setPetDisease("Fracture");
        pet4.setOwner(owner2);

        petRepository.save(pet3);
        petRepository.save(pet4);
    }

    @Test
    public void findTest(){
        Optional<Pet> findPet = petRepository.findById(2l);
        if(findPet.isPresent()){
            //지연로딩 시,
            assertThat(findPet.get().getPetName()).isEqualTo("Tom");//필요한 거만 select
//            기존에 연관객체를 필드로 가지고 있는 객체를 조회할 경우
//            지연로딩으로 설정했다면, 필드에 있는 연관객체에는 Proxy가 주입된다.
//            이 때 최초 Proxy는 원본 객체를 상속받고 필드도 그대로 가지고 있다.
//            하지만, ID값만 들어가고 나머지 필드는 NULL로 되어 있고,
//            ID 외에 다른 필드를 조회할 때 새로운 쿼리가 발생된다.

//            entityManager.flush();
//            entityManager.flush();

//            영속성 컨텍스트를 비우면 매핑 정보를 가지고 있던 객체가 없어지므로,
//            더 이상 필드에 있던 연관객체 조회 시 Proxy에 있던 ID를 제외한 다른 필드 조회가 불가능해진다.
//            log.info("owner id: " + findPet.get().getOwner().getOwnerId());//오너를 가져와야 할 때, 그 때 select한다.
            //트랜젝션으로 못 묶는 상황이 생기면 Id에 프록시를 넣어놓으면 조회가 가능하다..
            log.info("owner phone: " + findPet.get().getOwner().getOwnerPhone());
            log.info("owner: " + findPet.get().getOwner().getClass());

        }
    }

    @Test
    public void updateTest(){
        petRepository.findAll().get(0).getOwner().setOwnerName("Lazy Kim");
    }

    @Test
    public void manyToOneBothWaysTest(){
//        반려동물 이름으로 찾은 주인의 전체 반려동물 찾기
//        List<Pet> pets = petRepository.findByPetName("Tom");
//        pets.get(0).getOwner().getPets().stream().map(Pet::getPetName).forEach(log::info);

//        N + 1 문제 발생
//        조회하는 개수만큼 쿼리가 실행,  지연로딩 때문에 발생되는 문제,
//        List<Pet> pets = petRepository.findAll();//select 쿼리1개
//        for (Pet pet : pets){
//            log.info("pet name : "+ pet.getPetName());
//            log.info("owner : "+ pet.getOwner().getClass()); //proxy에서 나감.
//            log.info("owner name : "+ pet.getOwner().getOwnerName()); // n개의 쿼리가 나감.
//        }

//        fetchjoin으로 모든 문제 해결 : 내가 원하는 연관관계를 join
//        순수jpq, jpql방식
        List<Pet> pets = petRepository.findAllFetchJoin();
        for (Pet pet : pets){
            log.info("pet name : "+ pet.getPetName());
            log.info("owner : "+ pet.getOwner().getClass());
            log.info("owner name : "+ pet.getOwner().getOwnerName());
        }

//       spring data jpa방식
//        List<Pet> pets = petRepository.findAll();


   }










}
