package com.example.eccomerceproject.exception;

import jakarta.persistence.Id;

public class InvalidSellerException extends Exception{
    public InvalidSellerException(String msg){
        super(msg);
    }
}
