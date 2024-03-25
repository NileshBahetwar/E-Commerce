package com.example.eccomerceproject.service.impl;

import com.example.eccomerceproject.Transformer.ProductTransformer;
import com.example.eccomerceproject.dto.requestDto.ProductRequestDto;
import com.example.eccomerceproject.dto.requestDto.SellerRequestDto;
import com.example.eccomerceproject.dto.responseDto.ProductResponseDto;
import com.example.eccomerceproject.dto.responseDto.SellerResponseDto;
import com.example.eccomerceproject.enums.ProductCategory;
import com.example.eccomerceproject.enums.ProductStatus;
import com.example.eccomerceproject.exception.EmailAlreadyExistException;
import com.example.eccomerceproject.exception.InvalidSellerException;
import com.example.eccomerceproject.exception.OutOfStockException;
import com.example.eccomerceproject.exception.ProductNotFoundException;
import com.example.eccomerceproject.model.Item;
import com.example.eccomerceproject.model.Ordered;
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

    @Override
    public void decreaseProductOtQuantity(Item item) throws OutOfStockException {
          Product product = item.getProduct();
          int reqQuantity = item.getRequiredQuantity();
          int currQuantity = product.getQuantity();
          if(reqQuantity>currQuantity){
              throw new OutOfStockException("Product out of stock !!! ");
          }
          product.setQuantity(currQuantity-reqQuantity);
          if(product.getQuantity()==0){
              product.setProductStatus(ProductStatus.OUT_OF_STOCK);
          }
    }

    @Override
    public List<ProductResponseDto> getProductsOfSeller(String sellerEmailId) throws InvalidSellerException {
        Seller seller = sellerRepository.findByEmailId(sellerEmailId);
        if(seller==null){
            throw new InvalidSellerException("Seller does not exist with give mail id");
        }
        List<Product>products = seller.getProducts();
        List<ProductResponseDto>productList=new ArrayList<>();
        for(Product product : products){
            productList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productList;
    }

    public void deleteProduct(int sellerId,int productId) throws InvalidSellerException, ProductNotFoundException {
        Seller seller;
        try{
            seller = sellerRepository.findById(sellerId).get();
        }
        catch(Exception e){
            throw new InvalidSellerException("provided seller id is incorrect");
        }

        List<Product>products = seller.getProducts();
        Product product;
        try{
            product = productRepository.findById(productId).get();
        }
        catch(Exception e){
            throw new ProductNotFoundException("product with given id not available");
        }
        productRepository.deleteById(productId);
        //products.remove(product);
        product.setQuantity(product.getQuantity()-1);
        if(product.getQuantity()==0)
        product.setProductStatus(ProductStatus.OUT_OF_STOCK);

        sellerRepository.save(seller);
    }

    @Override
    public List<ProductResponseDto> getTop5products() {
        List<Product>getTop5 = productRepository.getTop5Products();
        List<ProductResponseDto>top5ProductsList = new ArrayList<>();
        for(Product product : getTop5){
            top5ProductsList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return top5ProductsList;
    }

    @Override
    public List<ProductResponseDto> getTop5CheapestProducts() {
        List<Product>getTop5 = productRepository.getTop5CheapestProducts();
        List<ProductResponseDto>top5CheapestProductList = new ArrayList<>();
        for(Product product : getTop5){
            top5CheapestProductList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return top5CheapestProductList;
    }

    @Override
    public List<ProductResponseDto> getAllAvailableProducts() {
        List<Product> products = productRepository.getAllAvailableProducts();
        List<ProductResponseDto>productList = new ArrayList<>();
        for(Product product : products){
            productList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productList;
    }

    @Override
    public List<ProductResponseDto> getAllOutOfStockProducts() {
        List<Product> products = productRepository.getAllOutOfStockProducts();
        List<ProductResponseDto>productList = new ArrayList<>();
        for(Product product : products){
            productList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productList;
    }

    @Override
    public List<ProductResponseDto> getAllProductsGreaterThan100Quantity(){
        List<Product> products = productRepository.getAllProductsGreaterThan100Quantity();
        List<ProductResponseDto>productList = new ArrayList<>();
        for(Product product : products){
            productList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productList;
    }

    @Override
    public ProductResponseDto getCheapestProductOfParticularCategory(String category) {
        Product product = productRepository.getCheapestProductOfParticularCategory(category);
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Override
    public ProductResponseDto getCostliestProductOfParticularCategory(String category) {
        Product product = productRepository.getCostliestProductOfParticularCategory(category);
        return ProductTransformer.ProductToProductResponseDto(product);
    }

}
