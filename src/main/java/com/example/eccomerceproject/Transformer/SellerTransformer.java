package com.example.eccomerceproject.Transformer;

import com.example.eccomerceproject.dto.requestDto.SellerRequestDto;
import com.example.eccomerceproject.dto.responseDto.SellerResponseDto;
import com.example.eccomerceproject.model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass // optional thing
// if we create an obj of static java compiler won't through an error
// it just programmer know that does not need to create an object
// but still if we want it should through an error if we create an object of it
// then we should use @UtilityClass
public class SellerTransformer {
    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .age(sellerRequestDto.getAge())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .name(sellerRequestDto.getName())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        SellerResponseDto sellerResponseDto = SellerResponseDto.builder()
                .age(seller.getAge())
                .name(seller.getName())
                .build();

        return sellerResponseDto;
    }
}
