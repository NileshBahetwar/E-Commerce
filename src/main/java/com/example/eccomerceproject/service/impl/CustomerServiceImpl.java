package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.Transformer.CustomerTransformer;
import com.example.eccomerceproject.dto.requestDto.CustomerRequestDto;
import com.example.eccomerceproject.dto.responseDto.CustomerResponseDto;
import com.example.eccomerceproject.exception.MobileNoAlreadyExistsException;
import com.example.eccomerceproject.model.Cart;
import com.example.eccomerceproject.model.Customer;
import com.example.eccomerceproject.repository.CustomerRepository;
import com.example.eccomerceproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException {
        if(customerRepository.findByMobNo(customerRequestDto.getMobNo()) !=null ){
            throw new MobileNoAlreadyExistsException("sorry !!! mobile number already in use");
        }

        // dto to entity
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        // cart adding as we can't add customer in x'mer
        Cart cart = Cart.builder()
                .customer(customer)
                .totalCost(0)
                .noOfItems(0)
                .build();

        customer.setCart(cart);
        Customer savedCustomer = customerRepository.save(customer);

        // entity to dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
