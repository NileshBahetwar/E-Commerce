package com.example.eccomerceproject.Transformer;

import com.example.eccomerceproject.dto.requestDto.CardRequestDto;
import com.example.eccomerceproject.dto.responseDto.CardResponseDto;
import com.example.eccomerceproject.model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardTransformer {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .cardType(cardRequestDto.getCardType())
                .build();
    }

//    public static CardResponseDto CardToCardResponseDto(Card card,String customerName) {
//        return CardResponseDto.builder()
//                .cardNo(card.getCardNo())
//                .customerName(customerName)
//                .build();
//    }
}
