package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.Transformer.SellerTransformer;
import com.example.eccomerceproject.dto.requestDto.SellerRequestDto;
import com.example.eccomerceproject.dto.responseDto.SellerResponseDto;
import com.example.eccomerceproject.exception.EmailAlreadyExistException;
import com.example.eccomerceproject.exception.InvalidSellerException;
import com.example.eccomerceproject.exception.InvalidSellerIdException;
import com.example.eccomerceproject.model.Seller;
import com.example.eccomerceproject.repository.SellerRepository;
import com.example.eccomerceproject.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public SellerResponseDto getSellerByEmail(String emailId) throws InvalidSellerException {
        Seller seller = sellerRepository.findByEmailId(emailId);
        if(seller==null){
            throw new InvalidSellerException("this email id incorrect,enter correct email id");
        }
        return SellerTransformer.SellerToSellerResponseDto(seller);
    }

    public SellerResponseDto getSellerById(int id) throws  InvalidSellerException {
        Seller seller = sellerRepository.findById(id).get();
        if(seller==null){
            throw new InvalidSellerException("Incorrect seller id !!!");
        }
        return SellerTransformer.SellerToSellerResponseDto(seller);
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        List<Seller>sellers=sellerRepository.findAll();
        List<SellerResponseDto>sellersList=new ArrayList<>();
        for(Seller seller : sellers){
            sellersList.add(SellerTransformer.SellerToSellerResponseDto(seller));
        }
        return sellersList;
    }


      public Seller updateSellerInfo(String emailId,String mobNo){
        Seller seller = sellerRepository.findByEmailId(emailId);
        seller.setMobNo(mobNo);
        return sellerRepository.save(seller);
      }

      public void deleteSeller(int id){
         sellerRepository.deleteById(id);
      }

      public List<SellerResponseDto>getAllSellerWithAge(int min,int max){
          List<Seller>sellers = sellerRepository.getAllSellerWithAge(min,max);
          List<SellerResponseDto>listOfSellers = new ArrayList<>();
          for(Seller seller : sellers){
              listOfSellers.add(SellerTransformer.SellerToSellerResponseDto(seller));
          }
          System.out.println(listOfSellers);
          return listOfSellers;
      }
}
