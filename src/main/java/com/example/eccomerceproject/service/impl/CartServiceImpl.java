package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.Transformer.CartTransformer;
import com.example.eccomerceproject.Transformer.ItemTransformer;
import com.example.eccomerceproject.dto.responseDto.CartResponseDto;
import com.example.eccomerceproject.dto.responseDto.ItemResponseDto;
import com.example.eccomerceproject.model.Cart;
import com.example.eccomerceproject.model.Customer;
import com.example.eccomerceproject.model.Item;
import com.example.eccomerceproject.repository.CartRepository;
import com.example.eccomerceproject.repository.CustomerRepository;
import com.example.eccomerceproject.repository.ItemRepository;
import com.example.eccomerceproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
  @Autowired
    CustomerRepository customerRepository;
  @Autowired
    CartRepository cartRepository;
  @Autowired
    ItemRepository itemRepository;
    @Override
    public CartResponseDto saveCart(int customerId, Item item) {
        Customer customer=customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getTotalCost()+item.getRequiredQuantity() * item.getProduct().getPrice();
        cart.setTotalCost(newTotal);
        cart.setNoOfItems(cart.getItems().size());
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartTransformer.CartToCartResponseDto(savedCart);

        List<Item> items = cart.getItems();//itemRepository.findAll();
        List<ItemResponseDto>listOfDtoItem=new ArrayList<>();
        for(Item currItem :items){
            listOfDtoItem.add(ItemTransformer.ItemToItemResponseDto(currItem));
        }
        cartResponseDto.setItemList(listOfDtoItem);

        return cartResponseDto;
    }
}
