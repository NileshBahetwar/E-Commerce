package com.example.eccomerceproject.service;

import com.example.eccomerceproject.dto.requestDto.CustomerRequestDto;
import com.example.eccomerceproject.dto.responseDto.CustomerResponseDto;
import com.example.eccomerceproject.exception.CustomerNotFoundException;
import com.example.eccomerceproject.exception.InvalidCustomerException;
import com.example.eccomerceproject.exception.InvalidEmailIdException;
import com.example.eccomerceproject.exception.MobileNoAlreadyExistsException;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException;
    public List<CustomerResponseDto> viewAllCustomer();
    public CustomerResponseDto getCustomerByEmail(String emailId);
    public List<CustomerResponseDto>customerBelow25();
    public List<CustomerResponseDto>getVisaUserCustomers();
    public void updateCustomerInfo(String emailId,String address) throws CustomerNotFoundException;
    public void deleteCustomerByEmail(String emailId) throws InvalidEmailIdException;
}
