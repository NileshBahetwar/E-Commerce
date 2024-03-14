package com.example.eccomerceproject.service;

import com.example.eccomerceproject.dto.requestDto.ProductRequestDto;
import com.example.eccomerceproject.dto.responseDto.ProductResponseDto;
import com.example.eccomerceproject.enums.ProductCategory;
import com.example.eccomerceproject.exception.InvalidSellerException;
import com.example.eccomerceproject.exception.OutOfStockException;
import com.example.eccomerceproject.model.Item;
import com.example.eccomerceproject.model.Ordered;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);
    public List<ProductResponseDto>getProductByPriceAndCategory(int price,String category);

    public void decreaseProductOtQuantity(Item item) throws OutOfStockException;
}
