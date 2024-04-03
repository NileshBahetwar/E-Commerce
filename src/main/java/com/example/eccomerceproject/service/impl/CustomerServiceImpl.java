package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.Transformer.CartTransformer;
import com.example.eccomerceproject.Transformer.CustomerTransformer;
import com.example.eccomerceproject.dto.requestDto.CustomerRequestDto;
import com.example.eccomerceproject.dto.responseDto.CustomerResponseDto;
import com.example.eccomerceproject.exception.CustomerNotFoundException;
import com.example.eccomerceproject.exception.InvalidCustomerException;
import com.example.eccomerceproject.exception.InvalidEmailIdException;
import com.example.eccomerceproject.exception.MobileNoAlreadyExistsException;
import com.example.eccomerceproject.model.Card;
import com.example.eccomerceproject.model.Cart;
import com.example.eccomerceproject.model.Customer;
import com.example.eccomerceproject.repository.CardRepository;
import com.example.eccomerceproject.repository.CustomerRepository;
import com.example.eccomerceproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
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

    @Override
    public List<CustomerResponseDto> viewAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customerList=new ArrayList<>();
        for(Customer customer : customers){
            customerList.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
        }
        return customerList;
    }

    @Override
    public CustomerResponseDto getCustomerByEmail(String emailId) {
        Customer customer = customerRepository.findByEmailId(emailId);
        return  CustomerTransformer.CustomerToCustomerResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> customerBelow25() {
        List<Customer>customers = customerRepository.customerBelow25();
        List<CustomerResponseDto>customerList=new ArrayList<>();
        for(Customer customer : customers){
            customerList.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
        }
        return customerList;
    }

    @Override
    public List<CustomerResponseDto> getVisaUserCustomers() {
        List<Card>cardList = cardRepository.findAllByCardType();
        System.out.println(cardList.size());
        List<CustomerResponseDto>customerList=new ArrayList<>();
        int cnt=0;
        for(Card  card : cardList){
            Customer customer = card.getCustomer();
                customerList.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
                cnt++;
        }
        System.out.println(cnt);
        return customerList;
    }

    @Override
    public void updateCustomerInfo(String emailId, String address) throws CustomerNotFoundException {
        Customer customer=customerRepository.findByEmailId(emailId);
            if(customer==null)
                throw new CustomerNotFoundException("customer does not exist with provided emailId");

        customer.setAddress(address);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerByEmail(String emailId) throws InvalidEmailIdException {
        Customer customer = customerRepository.findByEmailId(emailId);
        if(customer==null)
            throw new InvalidEmailIdException("incorrect email id !!!");

        customerRepository.deleteById(customer.getId());
    }
}
