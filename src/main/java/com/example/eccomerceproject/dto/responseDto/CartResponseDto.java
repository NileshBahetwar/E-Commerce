package com.example.eccomerceproject.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data  // getters,setters,RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {
    int cartTotal;
    int noOfItems;
    String customerName;
    List<ItemResponseDto> itemList;
}
