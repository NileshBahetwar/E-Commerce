package com.example.eccomerceproject.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data  // getters,setters,RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    int quantity;
    String productName;
    int priceOfOneItem;
    int totalPrice;
}
