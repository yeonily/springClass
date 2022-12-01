package com.example.myadvanced.repository;

import com.example.myadvanced.entity.Owner;
import com.example.myadvanced.entity.OwnerDTO;
import com.example.myadvanced.entity.QOwnerDTO;
import com.example.myadvanced.entity.Search;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.myadvanced.entity.QOwner.owner;

@Repository
@RequiredArgsConstructor
public class OwnerCustomRepositoryImpl implements OwnerCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Owner> findAllByOwnerName(Pageable pageable) {
        List<Owner> owners = jpaQueryFactory.selectFrom(owner)
                .where(owner.ownerName.eq("홍길동"))
                .orderBy(owner.ownerId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(owner)
                .where(owner.ownerName.eq("홍길동"))
                .fetch().size();//총 개수

        return new PageImpl<>(owners, pageable, total);
    }

    @Override
    public List<OwnerDTO> findAllByOwnerName(String ownerName) {
        return jpaQueryFactory.select(new QOwnerDTO(
                owner.ownerId,
                owner.ownerName,
                owner.ownerPhone,
                owner.ownerAge
        )).from(owner)
                .where(owner.ownerName.eq("홍길동"))
                .fetch();


//        return jpaQueryFactory.select(Projections.constructor(OwnerDTO.class,
//                owner.ownerId,
//                owner.ownerName,
//                owner.ownerPhone
//        )).from(owner)
//                .where(owner.ownerName.eq("홍길동"))
//                .fetch();


    }

    @Override
    public List<OwnerDTO> findAllSearch(Search search) {
        return jpaQueryFactory.select(new QOwnerDTO(owner.ownerId, owner.ownerName, owner.ownerPhone, owner.ownerAge))
                .from(owner)
                .where(
                        nameEq(search.getName()),
                        ageLoe(search.getAge())).fetch(); //null이면 생략됨.

    }

    private BooleanExpression nameEq(String name){ //querydsl 임포트
        return StringUtils.hasText(name) ? owner.ownerName.eq(name) : null; //springframework 임포트
                              //null이나 빈 문자열일 때 false
    }

    private BooleanExpression ageLoe(Integer age){
        return age != null ? owner.ownerAge.loe(age) : null; //Integer는 null검사
    }

    private BooleanExpression nameEqOrAgeLoe(String name, Integer age){
        return nameEq(name).or(ageLoe(age));
    }

    private BooleanExpression nameEqAndAgeLoe(String name, Integer age){
        return nameEq(name).and(ageLoe(age));
    }

}
