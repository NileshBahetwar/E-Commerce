package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.Transformer.SellerTransformer;
import com.example.eccomerceproject.dto.requestDto.SellerRequestDto;
import com.example.eccomerceproject.dto.responseDto.SellerResponseDto;
import com.example.eccomerceproject.exception.EmailAlreadyExistException;
import com.example.eccomerceproject.model.Seller;
import com.example.eccomerceproject.repository.SellerRepository;
import com.example.eccomerceproject.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyExistException {
//        Seller seller = new Seller();
//        seller.setAge(sellerRequestDto.getAge());
//        seller.setEmailId(sellerRequestDto.getEmailId());
//        seller.setMobNo(sellerRequestDto.getMobNo());
//        seller.setName(sellerRequestDto.getName());
//
//        sellerRepository.save(seller);

          if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null){
              throw new EmailAlreadyExistException("email id already registered !!!!");
          }

        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);
        // prepare responseDto

//        SellerResponseDto  sellerResponseDto = new SellerResponseDto();
//        sellerResponseDto.setName(seller.getName());
//        sellerResponseDto.setAge(seller.getAge());
//        return sellerResponseDto;

        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;
    }
}
