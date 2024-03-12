package com.example.eccomerceproject.service;

import com.example.eccomerceproject.dto.requestDto.ItemRequestDto;
import com.example.eccomerceproject.exception.InvalidCustomerException;
import com.example.eccomerceproject.exception.OutOfStockException;
import com.example.eccomerceproject.exception.ProductNotFoundException;
import com.example.eccomerceproject.exception.RequiredQuantityNotAvailableException;
import com.example.eccomerceproject.model.Item;

public interface ItemService {
    public Item addToCart(ItemRequestDto itemRequestDto) throws InvalidCustomerException, ProductNotFoundException, RequiredQuantityNotAvailableException, OutOfStockException;
}
