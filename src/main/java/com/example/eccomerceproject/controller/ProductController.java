package com.example.eccomerceproject.controller;

import com.example.eccomerceproject.dto.requestDto.ProductRequestDto;
import com.example.eccomerceproject.dto.responseDto.ProductResponseDto;
import com.example.eccomerceproject.enums.ProductCategory;
import com.example.eccomerceproject.exception.InvalidSellerException;
import com.example.eccomerceproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {
        return productService.addProduct(productRequestDto);
    }

    @GetMapping("/get/{category}")
    public List<ProductResponseDto>getAllProductsByCategory(@PathVariable("category") ProductCategory category){
        return productService.getAllProductsByCategory(category);
    }

    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto>getProductByPriceAndCategory(
            @PathVariable("price") int price,
            @PathVariable("category") String category){
        return productService.getProductByPriceAndCategory(price,category);
    }
}
