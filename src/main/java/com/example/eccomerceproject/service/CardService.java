package com.example.eccomerceproject.service;

import com.example.eccomerceproject.dto.requestDto.CardRequestDto;
import com.example.eccomerceproject.dto.responseDto.CardResponseDto;
import com.example.eccomerceproject.exception.InvalidCustomerException;
import org.springframework.stereotype.Service;


public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}
