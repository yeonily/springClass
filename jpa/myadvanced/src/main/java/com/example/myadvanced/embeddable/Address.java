package com.example.myadvanced.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Address {
    private String carOwnerZipCode;
    private String carOwnerAddress;
    private String carOwnerAddressDetail;

    public void create(String carOwnerZipCode, String carOwnerAddress, String carOwnerAddressDetail) {
        this.carOwnerZipCode = carOwnerZipCode;
        this.carOwnerAddress = carOwnerAddress;
        this.carOwnerAddressDetail = carOwnerAddressDetail;
    }
}
