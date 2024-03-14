package com.example.eccomerceproject.Transformer;

import com.example.eccomerceproject.dto.responseDto.OrderResponseDto;
import com.example.eccomerceproject.model.Ordered;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderTransformer {
    public static OrderResponseDto orderToOrderRequestDto(Ordered order){
        return OrderResponseDto.builder()
                .orderNo(order.getOrderNo())
                .cardUsed(order.getCardUsed())
                .customerName(order.getCustomer().getName())
                .totalValue(order.getTotalValue())
                .orderDate(order.getOrderDate())
                .build();
    }
}
