package com.example.eccomerceproject.dto.requestDto;

import com.example.eccomerceproject.enums.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data  // getters,setters,RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {
    String cardNo;
    String mobNo;
    int cvv;
    Date expiryDate;
    CardType cardType;
}
