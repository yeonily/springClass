package com.example.order_my.service;

import com.example.order_my.domain.dao.ItemDAO;
import com.example.order_my.domain.dao.OrderDAO;
import com.example.order_my.domain.vo.ItemVO;
import com.example.order_my.domain.vo.OrderDTO;
import com.example.order_my.domain.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {//어떤 테이블이 합쳐질 지 모르니까 interface로 해야 하는데, 지금은 실습이니 class로 구현
    private final ItemDAO itemDAO;
    private final OrderDAO orderDAO;

//    결제
//    외부에서 주문할 항목들을 전달받는다.
    public void addOrder(List<OrderVO> orders){//여러 개니까 가변인자로 받음.
        // OrderVO는 우리가 받을 정보들
        // OrderDTO는 연산해야 될 것들..

//        주문 번호를 1 증가시킨다.
        orderDAO.setOrderSequence();
        for (OrderVO order : orders){//order에는
            //재고관리
            ItemVO itemVO = itemDAO.findById(order.getItemNumber());
            itemVO.setItemStock(itemVO.getItemStock() - order.getItemCount());
            itemDAO.setItem(itemVO);

             //주문 번호를 생성한다.                      //주문번호는 처음에 null
            OrderDTO orderDTO = new OrderDTO(orderDAO.findId());//실제 데이터베이스에 들어가게 될 정보를 갖고 있어야 함.
            //화면에서 받아오는 값들, 정보들을 담아준다.
            orderDTO.setItemCount(order.getItemCount());
            orderDTO.setItemNumber(order.getItemNumber());
            //상품 1개 가격을 담는다.
            orderDTO.setItemPrice(itemDAO.findById(order.getItemNumber()).getItemPrice());
            //총 가격을 담는다.
            orderDTO.setOrderPrice();

            orderDAO.save(orderDTO);
        }
    }
//    취소
    public void cancel(String orderId){
        //재고관리
        for (OrderDTO order : orderDAO.findByOrderId(orderId)){
            ItemVO itemVO = itemDAO.findById(order.getItemNumber());
            itemVO.setItemStock(itemVO.getItemStock() + order.getItemCount());
            itemDAO.setItem(itemVO);
        }
        //취소
        orderDAO.deleteById(orderId);
    }
//    조회
    public List<OrderDTO> show(Long itemNumber){
        return orderDAO.findByItemNumber(itemNumber);
    }

//    전체조회
    public List<OrderDTO> showAll(){
        return orderDAO.findAll();
    }
}
