package com.example.eccomerceproject.service;

import com.example.eccomerceproject.dto.requestDto.SellerRequestDto;
import com.example.eccomerceproject.dto.responseDto.SellerResponseDto;
import com.example.eccomerceproject.exception.EmailAlreadyExistException;
import com.example.eccomerceproject.exception.InvalidSellerException;
import com.example.eccomerceproject.exception.InvalidSellerIdException;
import com.example.eccomerceproject.model.Seller;

import java.util.List;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyExistException;
    public SellerResponseDto getSellerByEmail(String emailId) throws InvalidSellerException;

//    public Seller getSellerById(Integer id);

    public List<SellerResponseDto> getAllSellers();

    public Seller updateSellerInfo(String emailId,String mobNo);

    public void deleteSeller(int id);

    public List<SellerResponseDto> getAllSellerWithAge(int min,int max);

    public SellerResponseDto getSellerById(int id) throws InvalidSellerException;
}
