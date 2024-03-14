package com.example.eccomerceproject.service;

import com.example.eccomerceproject.dto.requestDto.CheckOutCartRequestDto;
import com.example.eccomerceproject.dto.responseDto.CartResponseDto;
import com.example.eccomerceproject.dto.responseDto.OrderResponseDto;
import com.example.eccomerceproject.exception.*;
import com.example.eccomerceproject.model.Item;

public interface CartService {
    public CartResponseDto saveCart(int customerId, Item item);
    public OrderResponseDto checkoutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws InvalidCustomerException, EmptyCartException, InvalidCardException, ProductCurrentlyUnavailableException, OrderFailedException;
}
