package com.example.eccomerceproject.repository;

import com.example.eccomerceproject.dto.responseDto.ProductResponseDto;
import com.example.eccomerceproject.enums.ProductCategory;
import com.example.eccomerceproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductCategory(ProductCategory category);
   @Query(value = "select * from product p where p.price > :price and p.product_category = :category",nativeQuery = true)
    List<Product>getProductByPriceAndCategory(int price,String category);

//    @Query(value = "select * from product p where p.price > :price and p.productCategory = :category")
//    List<Product>getProductByPriceAndCategory(int price,ProductCategory category);
}
