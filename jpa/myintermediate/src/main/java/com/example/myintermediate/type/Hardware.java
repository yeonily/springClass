package com.example.myintermediate.type;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// 상속관계가 아니다
// (필드가 많거나) 값 필드만 개별적으로 사용되지 않고, 묶어서 한번에 사용될 때 Embedded 방식을 사용한다.
// 만약 Embeddable에 선언된 필드를 통채로 자주 사용할 경우에도 Embedded방식으로 설계한다.
@Embeddable // 모듈화
public class Hardware {
    @Column(name = "COMPUTER_RAM")
    private int computerRAM;
    @Column(name = "COMPUTER_SSD")
    private int computerSSD;
    @Column(name = "COMPUTER_GPU")
    private String computerGPU;
    @Column(name = "COMPUTER_PROCESSOR")
    private String computerProcessor;

    public void create(int computerRAM, int computerSSD, String computerGPU, String computerProcessor) {
        this.computerRAM = computerRAM;
        this.computerSSD = computerSSD;
        this.computerGPU = computerGPU;
        this.computerProcessor = computerProcessor;
    }
}
