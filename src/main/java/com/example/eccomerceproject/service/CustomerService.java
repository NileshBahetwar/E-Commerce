package com.example.eccomerceproject.service;

import com.example.eccomerceproject.dto.requestDto.CustomerRequestDto;
import com.example.eccomerceproject.dto.responseDto.CustomerResponseDto;
import com.example.eccomerceproject.exception.MobileNoAlreadyExistsException;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException;
}
