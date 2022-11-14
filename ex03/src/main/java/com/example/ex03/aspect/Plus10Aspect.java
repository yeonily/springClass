package com.example.ex03.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
@Slf4j
public class Plus10Aspect {

//    JoinPoint(시점) : 합류되는 시점
    @Around("@annotation(com.example.ex03.aspect.Plus10)")
    public Integer aroundPlus10(ProceedingJoinPoint proceedingJoinPoint){
        Integer result = 0;
        try {
            result = (Integer)proceedingJoinPoint.proceed();//핵심로직을 실행시킨 것(doAdd), 리턴값이 됨.
        } catch (Throwable throwable) { //point cut 예외 처리 가능
            throwable.printStackTrace();//강제종료는 아닌데 빨간줄로 표시
        }
        return result + 10;
    }
}
