package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.exception.ProductCurrentlyUnavailableException;
import com.example.eccomerceproject.model.*;
import com.example.eccomerceproject.service.OrderService;
import com.example.eccomerceproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductService productService;
    @Override
    public Ordered placeOrder(Customer customer, Card card) throws ProductCurrentlyUnavailableException {
        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        //String cardNo = card.getCardNo();
        String maskCardNo = getMaskCard(card.getCardNo());

        order.setCardUsed(maskCardNo);
        order.setCustomer(customer);

        // cannot set no of items directly
        List<Item>orderedItems=new ArrayList<>();
        for(Item currItem : cart.getItems()){
             try{
                 productService.decreaseProductOtQuantity(currItem);
                 orderedItems.add(currItem);
             }
             catch (Exception e){
                 throw new ProductCurrentlyUnavailableException("Product out of stock !!! ");
             }
        }
        order.setItems(orderedItems);
        order.setTotalValue(cart.getTotalCost());

        return order;
    }
    public String getMaskCard(String cardNo){
        String maskCardNo = "";
        for(int i=0;i<cardNo.length()-4;i++){
            maskCardNo+='X';
        }
        maskCardNo+=cardNo.substring(cardNo.length()-4);
        return maskCardNo;
    }
}
