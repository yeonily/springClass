package com.example.order_my.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
public class OrderDTO {
    @NonNull //null이면 안 된다.
    private String orderId;
    private String orderDate;
    private Long itemNumber;
    private String itemName;
    private int itemCount;
    private int orderPrice;
    private int itemPrice; //각 item별 가격이 필요해서 DTO를 만들었다.
    private int itemStock;

    public void setOrderPrice() { // 직접 setter를 만들었고, 다른 곳에서 쓸 수 없다.
        this.orderPrice = this.itemCount * this.itemPrice;
    }

    public void setItemNumber(Long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    // 원래 가변인자를 써서 (외부에서 item이 몇 개 들어올 지 모르니까..)
    // setItems를 해줘야 함.
    // 난이도가 올라가니까 Test에서 list를 만들어서 해결.
}