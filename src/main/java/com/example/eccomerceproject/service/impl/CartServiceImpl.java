package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.Transformer.CartTransformer;
import com.example.eccomerceproject.Transformer.ItemTransformer;
import com.example.eccomerceproject.Transformer.OrderTransformer;
import com.example.eccomerceproject.dto.requestDto.CheckOutCartRequestDto;
import com.example.eccomerceproject.dto.responseDto.CartResponseDto;
import com.example.eccomerceproject.dto.responseDto.ItemResponseDto;
import com.example.eccomerceproject.dto.responseDto.OrderResponseDto;
import com.example.eccomerceproject.exception.*;
import com.example.eccomerceproject.model.*;
import com.example.eccomerceproject.repository.*;
import com.example.eccomerceproject.service.CartService;
import com.example.eccomerceproject.service.OrderService;
import com.example.eccomerceproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
  @Autowired
    CustomerRepository customerRepository;
  @Autowired
    CartRepository cartRepository;
  @Autowired
    ItemRepository itemRepository;
  @Autowired
  CardRepository cardRepository;
  @Autowired
  OrderService orderService;
  @Autowired
    OrderRepository orderRepository;
  @Autowired
    ProductService productService;
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

    @Override
    public OrderResponseDto checkoutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws InvalidCustomerException, EmptyCartException, InvalidCardException, ProductCurrentlyUnavailableException, OrderFailedException {
        Customer customer;
        try{
            customer = customerRepository.findById(checkOutCartRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new InvalidCustomerException("Invalid customer id !!!");
        }

        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());

        if(card==null || card.getCvv()!=checkOutCartRequestDto.getCvv() || card.getCustomer()!=customer){
              throw new InvalidCardException("Your card is not valid !!!");
        }

        Cart cart = customer.getCart();

        if(cart.getNoOfItems()==0){
            throw new EmptyCartException("Cart is empty !!!");
        }

        Ordered savedOrder;
        try {
            Ordered order = orderService.placeOrder(customer, card);
            customer.getListOfOrder().add(order);
             savedOrder = orderRepository.save(order);
            // need to reset the card and reduce the quantity of product
            resetCart(cart);
        }
        catch(Exception e){
            throw new OrderFailedException("Order cannot be made");
        }
       // prepare response dto
       OrderResponseDto orderResponseDto=OrderTransformer.orderToOrderResponseDto(savedOrder);
        List<ItemResponseDto>itemResponseDtoList = new ArrayList<>();
        for(Item item : savedOrder.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }
        orderResponseDto.setItems(itemResponseDtoList);
        return orderResponseDto;
    }
    public void resetCart(Cart cart){
        cart.setNoOfItems(0);
        cart.setTotalCost(0);
        cart.setItems(new ArrayList<>());
        //cartRepository.save(cart);
    }
}
