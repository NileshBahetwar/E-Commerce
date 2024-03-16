package com.example.eccomerceproject.controller;

import com.example.eccomerceproject.dto.requestDto.SellerRequestDto;
import com.example.eccomerceproject.dto.responseDto.SellerResponseDto;
import com.example.eccomerceproject.exception.InvalidSellerException;
import com.example.eccomerceproject.model.Seller;
import com.example.eccomerceproject.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        try {
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            // we will e.getMessage() instead of e because we don't want to show an exception to user
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-seller-by-email")
    public SellerResponseDto getSellerByEmail(@RequestParam String emailId){
        return sellerService.getSellerByEmail(emailId);
    }

    @GetMapping("/get-seller-by-id")
    public SellerResponseDto getSellerById(@RequestParam int id) throws InvalidSellerException {
        return sellerService.getSellerById(id);
    }
     @GetMapping("/get-all-sellers")
    public List<SellerResponseDto> getAllSellers(){
        return sellerService.getAllSellers();
    }
    @PutMapping("/update-mobNo")
    public String updateSellerInfo(@RequestParam String emailId,@RequestParam String mobNo){
        Seller seller = sellerService.updateSellerInfo(emailId,mobNo);
        return "mobNo get updated with new number whose name is "+seller.getName()+" having number: "+mobNo;
    }

    @DeleteMapping("/delete")
    public String deleteSeller(@RequestParam int id){
       sellerService.deleteSeller(id);
        return "seller deleted with provided id was "+id;
    }

    @GetMapping("/get/all-seller-with-age")
    public List<SellerResponseDto>getAllSellerWithAge(@RequestParam int min,@RequestParam int max){
        return sellerService.getAllSellerWithAge(min,max);
    }
}
