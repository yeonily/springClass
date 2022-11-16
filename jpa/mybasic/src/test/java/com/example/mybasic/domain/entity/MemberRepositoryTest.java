package com.example.mybasic.domain.entity;

import com.example.mybasic.repository.MemberRepository;
import com.example.mybasic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)//단위테스트에서는 자동 롤백이 됨.
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        Member member = new Member();
        member.create("김지연", "aaazxcv@gmail.com", "1235", 29, MemberType.ADMIN);
//        member.create("김장미", "aaa@gmail.com", "1215", 20, MemberType.ADMIN);
//        memberRepository.save(member);
        log.info("saved member: "+ memberRepository.save(member));
    }

    @Test
    public void findByIdTest(){
        assertThat(memberRepository.findById(3L)).isNotEmpty();
//  검증하면, 수정하기가 편함.
    }

    @Test
    public void findAllTest(){
        assertThat(memberRepository.findAll().stream().map(Member::getMemberName).collect(Collectors.toList()).get(0)).isEqualTo("김지연");
    }

    //더티체킹 :변경감지
    @Test
    public void updateTest(){
        memberRepository.findById(3l).get().setMemberPassword("9999");
        //널인지 아닌지 검사하기
    }

    @Test
    public void deleteTest(){
        memberRepository.deleteById(3l);
    }

    @Test
    public void findByMemberNameContainingTest(){
        memberRepository.findByMemberNameContaining("김").stream().map(Member::toString).forEach(log::info);
    }

    @Test
    public void fineMemberByMemberAgeTest(){
        memberRepository.findMemberByMemberAge(MemberType.ADMIN, 20).stream().map(Member::toString).forEach(log::info);
    }

    @Test
    public void findMemberByNamesTest(){
        memberRepository.findMemberByNames(Arrays.asList("김지연", "김유훈")).stream().map(Member::toString).forEach(log::info);
    }

    @Test
    public void updateByName(){
        Member member = memberRepository.findById(3L).get();
        memberRepository.updateByName(20);//클리어하고
        member = memberRepository.findById(3L).get();//한번더 호출해야 함. db select
        assertThat(member.getMemberAge()).isEqualTo(26);
    }

    @Test
    public void deleteByMemberAgeTest(){
        Member member = memberRepository.findById(3L).get();
        memberRepository.deleteByMemberAge(20);
        log.info("isPresent: "+ memberRepository.findById(3l).isPresent());
    }
}
