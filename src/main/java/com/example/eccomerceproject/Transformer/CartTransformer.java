package com.example.eccomerceproject.Transformer;

import com.example.eccomerceproject.dto.responseDto.CartResponseDto;
import com.example.eccomerceproject.model.Cart;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart){
        // list of cartResponseDto we will do in service layer
        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .noOfItems(cart.getNoOfItems())
                .cartTotal(cart.getTotalCost())
                .build();
    }
}
