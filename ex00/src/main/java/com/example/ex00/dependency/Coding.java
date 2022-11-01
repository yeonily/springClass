package com.example.ex00.dependency;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Data
@Getter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
public class Coding {
//    필드 주입
//    굉장히 편하게 주입할 수 있으나 순환 참조 시 오류가 발생하지 않기 때문에 StackOverFlow 발생.
//    final을 붙일 수 없기 때문에 다른 곳에서 변형 가능
//    @Autowired
    private final Computer computer;

//    생성자 주입
//    순환 참조 시 컴파일러 인지 가능, 오류 발생
//    메모리에 할당되면서 초기값으로 주입되므로 final 키워드 사용 가능, 다른 곳에서 변형 불가능
//	  의존성 주입이 되지 않으면 객체가 생성되지 않으므로 NPE 방어
//    생성자에 @Autowired 생략 가능
//    @Autowired
//    public Coding(Computer computer) {
//        this.computer = computer;
//    }
}
