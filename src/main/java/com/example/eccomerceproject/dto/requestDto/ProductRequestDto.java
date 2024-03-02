package com.example.eccomerceproject.dto.requestDto;

import com.example.eccomerceproject.enums.ProductCategory;
import com.example.eccomerceproject.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data  // getters,setters,RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
  int sellerId;
  String productName;
  int price;
  int quantity;
  ProductCategory productCategory;
  //ProductStatus productStatus;
}
