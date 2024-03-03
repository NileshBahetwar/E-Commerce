package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.Transformer.CardTransformer;
import com.example.eccomerceproject.dto.requestDto.CardRequestDto;
import com.example.eccomerceproject.dto.responseDto.CardResponseDto;
import com.example.eccomerceproject.exception.InvalidCustomerException;
import com.example.eccomerceproject.model.Card;
import com.example.eccomerceproject.model.Customer;
import com.example.eccomerceproject.repository.CardRepository;
import com.example.eccomerceproject.repository.CustomerRepository;
import com.example.eccomerceproject.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if(customer==null){
            throw new InvalidCustomerException("Customer does not exists !!!");
        }
        // RequestDto to entity
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCard().add(card);
        customerRepository.save(customer);

        // entity to responseDto
        return CardResponseDto.builder()
                .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();
       // return CardTransformer.CardToCardResponseDto(card,customer.getName());
    }
}
