package com.example.eccomerceproject.controller;

import com.example.eccomerceproject.dto.requestDto.CheckOutCartRequestDto;
import com.example.eccomerceproject.dto.requestDto.ItemRequestDto;
import com.example.eccomerceproject.dto.responseDto.CartResponseDto;
import com.example.eccomerceproject.dto.responseDto.OrderResponseDto;
import com.example.eccomerceproject.exception.*;
import com.example.eccomerceproject.model.Item;
import com.example.eccomerceproject.service.CartService;
import com.example.eccomerceproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws OutOfStockException, RequiredQuantityNotAvailableException, InvalidCustomerException, ProductNotFoundException {
        try{
            Item item = itemService.addToCart(itemRequestDto);
            // customerId and Item
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(),item);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkout")
    public OrderResponseDto checkoutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto) throws EmptyCartException, ProductCurrentlyUnavailableException, InvalidCustomerException, InvalidCardException, OrderFailedException {
           return cartService.checkoutCart(checkOutCartRequestDto);
    }
}
