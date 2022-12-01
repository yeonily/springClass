package com.example.advanced.entity.task;

import javax.persistence.*;

@Entity
@Table(name = "TBL_SUPER_CAR_REGISTRATION")
public class SuperCarRegistration extends Period{
    @Id @GeneratedValue
    private Long carRegistrationId;

    @ManyToOne(fetch = FetchType.LAZY)//객체진영의 작업 //지연로딩
    @JoinColumn(name = "SUPER_CAR_ID")//RDB에서 fk컬럼 알려주기, name = 실제 DB의 PK 컬럼명을 써야 함.
    private SuperCar superCar;

    @ManyToOne(fetch = FetchType.LAZY)//객체진영의 작업 //지연로딩
    @JoinColumn(name = "SUPER_CAR_OWNER_ID")//RDB에서 fk컬럼 알려주기, name = 실제 DB의 PK 컬럼명을 써야 함.
    private SuperCarOwner superCarOwner;

}
