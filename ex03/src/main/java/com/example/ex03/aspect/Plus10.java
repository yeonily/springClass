package com.example.ex03.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //메소드 위에 붙이는 어노테이션
@Retention(RetentionPolicy.RUNTIME) //실행 중에 되게끔 설정
public @interface Plus10 {;} //분리된 횡단 관심사
