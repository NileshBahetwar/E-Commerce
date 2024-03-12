package com.example.eccomerceproject.Transformer;

import com.example.eccomerceproject.dto.requestDto.ItemRequestDto;
import com.example.eccomerceproject.dto.responseDto.ItemResponseDto;
import com.example.eccomerceproject.model.Item;

public class ItemTransformer {
    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }
    public static ItemResponseDto ItemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .totalPrice(item.getProduct().getPrice() * item.getProduct().getQuantity())
                .productName(item.getProduct().getNameOfProduct())
                .quantity(item.getProduct().getQuantity())
                .build();
    }
}
