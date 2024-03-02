package com.example.eccomerceproject.Transformer;

import com.example.eccomerceproject.dto.requestDto.ProductRequestDto;
import com.example.eccomerceproject.dto.responseDto.ProductResponseDto;
import com.example.eccomerceproject.enums.ProductStatus;
import com.example.eccomerceproject.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass // optional thing
// if we create an obj of static java compiler won't through an error
// it just programmer know that does not need to create an object
// but still if we want it should through an error if we create an object of it
// then we should use @UtilityClass
public class ProductTransformer {

    // int sellerId;
    //  String productName;
    //  int price;
    //  int quantity;
    //  String productCategory;
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .nameOfProduct(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                // by default available for 1st time >> hardcoding
                .productStatus(ProductStatus.AVAILABLE)
                // we are not able to set seller obj here so, we will do it in service layer itself
        .build();
    }
    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getNameOfProduct())
                .productStatus(product.getProductStatus())
                .quantity(product.getQuantity())
                .sellerName(product.getSeller().getName())
                .build();
    }
}
