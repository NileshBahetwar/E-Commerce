package com.example.eccomerceproject.dto.responseDto;

import com.example.eccomerceproject.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data  // getters,setters,RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String productName;
    ProductStatus productStatus;
    String sellerName;
    int quantity;
    int price;
}
