package com.example.mybasic.domain.entity;

import com.example.mybasic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class EntityTest {

    @PersistenceContext//영속 컨텍스트를 등록
    private EntityManager entityManager;

    @Test
    @Transactional
    @Rollback(false)//단위테스트에만 사용가능, 자동롤백 방지
    public void memberTest(){
        Member memberA = new Member();
        Member memberB = new Member();

        memberA.setMemberName("한동석");
        memberA.setMemberEmail("tedhan1204@gmail.com");
        memberA.setMemberPassword("1234");
        memberA.setMemberAge(20);
        memberA.setMemberType(MemberType.MEMBER);

        memberB.setMemberName("장선홍");
        memberB.setMemberEmail("jsh0419@gmail.com");
        memberB.setMemberPassword("5060");
        memberB.setMemberAge(20);
        memberB.setMemberType(MemberType.ADMIN);

//        영속성 컨텍스트에 등록, 1차 캐시에 저장
        entityManager.persist(memberA);
        entityManager.persist(memberB);

//        영속성 컨텍스트에 등록된 정보를 SQL로 방출, 캐시는 그대로 유지
//        원래 flush()는 커밋 전에 발동된다.(여기서는 강제로 한 것임)
        entityManager.flush();//쿼리를 날린 것임. 캐시랑 관계 없음.

//        영속성 컨텍스트에 있는 1차 캐시를 없애준다.
        entityManager.clear();

//        캐시에 해당 ID가 있다면 SQL 조회 없이 캐시에서 가져온다(성능 최적화)
        Member findMember1 = entityManager.find(Member.class, 1L);

//        entityManager.flush();
//        entityManager.clear();

        Member findMember2 = entityManager.find(Member.class, 1L);

//        더티 체킹
        findMember2.setMemberPassword("0001");

//        entityManager.flush();//1차 캐시에 기억된 상태, 실무에서 사용할 수도 있음.
//        entityManager.clear();//실무에서 거의 사용할 일이 없음.

//        거의 사용 안함.
//        entityManager.detach(findMember2); //비영속성 상태로 detach
//        entityManager.merge(findMember2); //비영속 상태에서 영속상태로 merge하는 것.

//        삭제를 할 경우 영속상태인 객체만 가능하다.
        entityManager.remove(findMember2);

        entityManager.flush();
        entityManager.clear();

        if(Optional.ofNullable(findMember1).isPresent()){
            assertThat(findMember1.getMemberEmail()).isEqualTo("tedhan1204@gmail.com");
//            SELECT 쿼리 후 flush 후 1차 캐시에 저장된 객체를 다시 조회하면 값 비교는 항상 true이다.
            assertThat(findMember1).isEqualTo(findMember2);

        }
    }


//    @Test
//    @Transactional
//    @Rollback(false)//단위테스트에만 사용가능, 자동롤백 방지
//    public void superCarTest(){
//        SuperCar superCarA = new SuperCar();
//        SuperCar superCarB = new SuperCar();
//
//        superCarA.setSuperCarName("라리");
//        superCarA.setSuperCarPrice(10000);
//        superCarA.setSuperCarColor("white");
//        superCarA.setSuperCarBrand("페라리");
//
//        superCarB.setSuperCarName("기니");
//        superCarB.setSuperCarPrice(2000);
//        superCarB.setSuperCarColor("yellow");
//        superCarB.setSuperCarBrand("람보르기니");
//
////        영속성 컨텍스트에 등록, 1차 캐시에 저장
//        entityManager.persist(superCarA);
//        entityManager.persist(superCarB);
//
////        entityManager.flush();//쿼리를 날린 것임. 캐시랑 관계 없음.
//
////        영속성 컨텍스트에 있는 1차 캐시를 없애준다.
////        entityManager.clear();
//
////        캐시에 해당 ID가 있다면 SQL 조회 없이 캐시에서 가져온다(성능 최적화)
//        SuperCar findSuperCar1 = entityManager.find(SuperCar.class, 1L);
//        SuperCar findSuperCar2 = entityManager.find(SuperCar.class, 1L);
//
////        더티 체킹
//        findSuperCar2.setSuperCarColor("black");
//
////        삭제를 할 경우 영속상태인 객체만 가능하다.
//        entityManager.remove(findSuperCar2);
//
////        entityManager.flush();
////        entityManager.clear();
//
//        if(Optional.ofNullable(findSuperCar1).isPresent()){
//            assertThat(findSuperCar1.getSuperCarBrand()).isEqualTo("페라리");
////            SELECT 쿼리 후 flush 후 1차 캐시에 저장된 객체를 다시 조회하면 값 비교는 항상 true이다.
//            assertThat(findSuperCar1).isEqualTo(findSuperCar2);
//
//        }
//    }
}


