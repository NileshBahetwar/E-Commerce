package com.example.eccomerceproject.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data  // getters,setters,RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemRequestDto {
    int requiredQuantity;
    int productId;
    int customerId;
}
