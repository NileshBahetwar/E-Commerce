package com.example.eccomerceproject.service;

import com.example.eccomerceproject.exception.ProductCurrentlyUnavailableException;
import com.example.eccomerceproject.model.Card;
import com.example.eccomerceproject.model.Customer;
import com.example.eccomerceproject.model.Ordered;

public interface OrderService {
    public Ordered placeOrder(Customer customer, Card card) throws ProductCurrentlyUnavailableException;
}
