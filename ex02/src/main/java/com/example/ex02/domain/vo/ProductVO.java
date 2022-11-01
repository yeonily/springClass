package com.example.ex02.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductVO {
    private String productName;
    private int productPrice;
    private int productStock;
    private String productBrand;
}