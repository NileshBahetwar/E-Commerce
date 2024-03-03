package com.example.eccomerceproject.Transformer;

import com.example.eccomerceproject.dto.requestDto.CustomerRequestDto;
import com.example.eccomerceproject.dto.responseDto.CustomerResponseDto;
import com.example.eccomerceproject.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .address(customerRequestDto.getAddress())
                .emailId(customerRequestDto.getEmailId())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("customer registered with name "+customer.getName()+" successfully !!!")
        .build();
    }
}
