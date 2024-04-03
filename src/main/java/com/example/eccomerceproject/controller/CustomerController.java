package com.example.eccomerceproject.controller;

import com.example.eccomerceproject.dto.requestDto.CustomerRequestDto;
import com.example.eccomerceproject.dto.responseDto.CustomerResponseDto;
import com.example.eccomerceproject.exception.CustomerNotFoundException;
import com.example.eccomerceproject.exception.InvalidCustomerException;
import com.example.eccomerceproject.exception.InvalidEmailIdException;
import com.example.eccomerceproject.exception.MobileNoAlreadyExistsException;
import com.example.eccomerceproject.model.Customer;
import com.example.eccomerceproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class  CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException {
        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/get/all/customers")
    public List<CustomerResponseDto> viewAllCustomer(){
        return customerService.viewAllCustomer();
    }

    @GetMapping("/get/customer/by-email")
    public CustomerResponseDto getCustomerByEmail(@RequestParam String emailId){
        return customerService.getCustomerByEmail(emailId);
    }

    @GetMapping("/get/customer/below25")
    public List<CustomerResponseDto>customerAbove21(){
        return customerService.customerBelow25();
    }
    @GetMapping("/get/visa_card/customers")
    public List<CustomerResponseDto>getVisaUserCustomers(){
        return customerService.getVisaUserCustomers();
    }
    @PutMapping("/update/customer_info")
    public ResponseEntity updateCustomerInfo(@RequestParam String emailId,@RequestParam String address) throws CustomerNotFoundException {
        try {
            customerService.updateCustomerInfo(emailId, address);
            return new ResponseEntity("customer info updated successfully", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/customer-by-email")
    public ResponseEntity deleteCustomerBtEmail(@RequestParam String emailId) throws InvalidEmailIdException {
//        customerService.deleteCustomerBtEmail(emailId);
        //return "customer removed successfully";

        try {
            customerService.deleteCustomerByEmail(emailId);
            return new ResponseEntity("customer removed successfully", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
