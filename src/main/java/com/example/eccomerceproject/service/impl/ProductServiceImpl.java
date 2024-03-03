package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.Transformer.ProductTransformer;
import com.example.eccomerceproject.dto.requestDto.ProductRequestDto;
import com.example.eccomerceproject.dto.requestDto.SellerRequestDto;
import com.example.eccomerceproject.dto.responseDto.ProductResponseDto;
import com.example.eccomerceproject.dto.responseDto.SellerResponseDto;
import com.example.eccomerceproject.enums.ProductCategory;
import com.example.eccomerceproject.exception.EmailAlreadyExistException;
import com.example.eccomerceproject.exception.InvalidSellerException;
import com.example.eccomerceproject.model.Product;
import com.example.eccomerceproject.model.Seller;
import com.example.eccomerceproject.repository.ProductRepository;
import com.example.eccomerceproject.repository.SellerRepository;
import com.example.eccomerceproject.service.ProductService;
import com.example.eccomerceproject.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch(Exception e){
            throw new InvalidSellerException("seller does not found !!!");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        // we were not able to set seller id so, we are doing it this layer explicitly
        product.setSeller(seller);

        // add product to current product of seller
        seller.getProducts().add(product);

        sellerRepository.save(seller);

        // prepare response entity
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category) {
        List<Product>products=productRepository.findByProductCategory(category);
        List<ProductResponseDto>allProducts=new ArrayList<>();

        for(Product product : products){
            ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(product);
            allProducts.add(productResponseDto);
        }
        return allProducts;
    }

    @Override
    public List<ProductResponseDto> getProductByPriceAndCategory(int price, String category) {
        List<Product>products = productRepository.getProductByPriceAndCategory(price,category);
        List<ProductResponseDto>productResponseDtos=new ArrayList<>();

        for(Product product : products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtos;
    }
}
