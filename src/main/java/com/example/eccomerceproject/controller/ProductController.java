package com.example.eccomerceproject.controller;

import com.example.eccomerceproject.dto.requestDto.ProductRequestDto;
import com.example.eccomerceproject.dto.responseDto.ProductResponseDto;
import com.example.eccomerceproject.enums.ProductCategory;
import com.example.eccomerceproject.exception.InvalidSellerException;
import com.example.eccomerceproject.exception.ProductNotFoundException;
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
    @GetMapping("/get-all-products/{sellerEmailId}")
    public List<ProductResponseDto>getProductsOfSeller(@PathVariable String sellerEmailId) throws InvalidSellerException {
        return productService.getProductsOfSeller(sellerEmailId);
    }
    @DeleteMapping("/delete")
    public String deleteProduct(@RequestParam int sellerId,@RequestParam int productId) throws InvalidSellerException, ProductNotFoundException {
        productService.deleteProduct(sellerId,productId);
        return "product has been deleted with given product id "+productId+" of seller whose id is "+sellerId;
    }
    @GetMapping("/get_top_5_cheapest_products")
    public List<ProductResponseDto>getTop5Products(){
        return productService.getTop5products();
    }
    @GetMapping("/get_top_5_costliest_products")
    public List<ProductResponseDto>getTop5CheapestProducts(){
        return productService.getTop5CheapestProducts();
    }
    @GetMapping("/get-all-available-products")
    public List<ProductResponseDto>getAllAvailableProducts(){
        return productService.getAllAvailableProducts();
    }
    @GetMapping("/get-all-out-of-stock-products")
    public List<ProductResponseDto>getAllOutOfStockProducts(){
        return productService.getAllOutOfStockProducts();
    }

    @GetMapping("/get-all-more-than-100-products quantity")
    public List<ProductResponseDto>getAllProductsGreaterThan100Quantity(){
        return productService.getAllProductsGreaterThan100Quantity();
    }
    @GetMapping("/get/cheapest-product/with_category/{category}")
    public ProductResponseDto getCheapestProductOfParticularCategory(@PathVariable("category") String category){
        return productService.getCheapestProductOfParticularCategory(category);
    }
    @GetMapping("/get/costliest-product/with_category/{category}")
    public ProductResponseDto getCostliestProductOfParticularCategory(@PathVariable("category") String category){
        return productService.getCostliestProductOfParticularCategory(category);
    }
}
