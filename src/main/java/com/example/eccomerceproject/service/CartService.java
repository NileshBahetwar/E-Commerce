package com.example.eccomerceproject.service;

import com.example.eccomerceproject.dto.responseDto.CartResponseDto;
import com.example.eccomerceproject.model.Item;

public interface CartService {
    public CartResponseDto saveCart(int customerId, Item item);
}
