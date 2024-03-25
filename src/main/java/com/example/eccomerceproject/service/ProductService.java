package com.example.eccomerceproject.service;

import com.example.eccomerceproject.dto.requestDto.ProductRequestDto;
import com.example.eccomerceproject.dto.responseDto.ProductResponseDto;
import com.example.eccomerceproject.enums.ProductCategory;
import com.example.eccomerceproject.exception.InvalidSellerException;
import com.example.eccomerceproject.exception.OutOfStockException;
import com.example.eccomerceproject.exception.ProductNotFoundException;
import com.example.eccomerceproject.model.Item;
import com.example.eccomerceproject.model.Ordered;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);
    public List<ProductResponseDto>getProductByPriceAndCategory(int price,String category);

    public void decreaseProductOtQuantity(Item item) throws OutOfStockException;
    public List<ProductResponseDto>getProductsOfSeller(String sellerEmailId) throws InvalidSellerException;
    public void deleteProduct(int sellerId,int productId) throws InvalidSellerException, ProductNotFoundException;
    public List<ProductResponseDto>getTop5products();
    public List<ProductResponseDto>getTop5CheapestProducts();
    public List<ProductResponseDto>getAllAvailableProducts();
    public List<ProductResponseDto>getAllOutOfStockProducts();
    public List<ProductResponseDto>getAllProductsGreaterThan100Quantity();
    public ProductResponseDto getCheapestProductOfParticularCategory(String category);

    public ProductResponseDto getCostliestProductOfParticularCategory(String category);
}
