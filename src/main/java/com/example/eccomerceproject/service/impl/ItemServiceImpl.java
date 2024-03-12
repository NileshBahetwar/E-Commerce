package com.example.eccomerceproject.service.impl;


import com.example.eccomerceproject.Transformer.ItemTransformer;
import com.example.eccomerceproject.dto.requestDto.ItemRequestDto;
import com.example.eccomerceproject.enums.ProductStatus;
import com.example.eccomerceproject.exception.InvalidCustomerException;
import com.example.eccomerceproject.exception.OutOfStockException;
import com.example.eccomerceproject.exception.ProductNotFoundException;
import com.example.eccomerceproject.exception.RequiredQuantityNotAvailableException;
import com.example.eccomerceproject.model.Customer;
import com.example.eccomerceproject.model.Item;
import com.example.eccomerceproject.model.Product;
import com.example.eccomerceproject.repository.CustomerRepository;
import com.example.eccomerceproject.repository.ProductRepository;
import com.example.eccomerceproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public Item addToCart(ItemRequestDto itemRequestDto) throws InvalidCustomerException, ProductNotFoundException, RequiredQuantityNotAvailableException, OutOfStockException {
        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new InvalidCustomerException("customer doesn't exists !!! ");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new ProductNotFoundException("Product unavailable now please wait for some time !!!");
        }

        if(itemRequestDto.getRequiredQuantity()>product.getQuantity()){
            throw new RequiredQuantityNotAvailableException("required quantity of product not available please wait for some time");
        }

        if(product.getProductStatus() != ProductStatus.AVAILABLE){
            throw new OutOfStockException("product is out of stock currently!!!");
        }

        //  DTO to entity
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItems().add(item);

        Product savedProduct = productRepository.save(product);

        int size = savedProduct.getItems().size();
        return savedProduct.getItems().get(size-1);
    }
}
