package com.example.eccomerceproject.dto.responseDto;

import com.example.eccomerceproject.model.Customer;
import com.example.eccomerceproject.model.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data  // getters,setters,RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String customerName;
    String orderNo;
    int totalValue;
    Date orderDate;
    String cardUsed;
    List<ItemResponseDto> items;
}
