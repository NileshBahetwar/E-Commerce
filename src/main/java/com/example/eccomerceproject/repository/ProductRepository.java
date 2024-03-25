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
    @Query(value = "select * from product order by price LIMIT 5",nativeQuery = true)
    List<Product>getTop5Products();

    @Query(value = "select * from product order by price desc LIMIT 5",nativeQuery = true)
    List<Product>getTop5CheapestProducts();
    @Query(value = "select * from product where product_status ='available'",nativeQuery = true)
    List<Product>getAllAvailableProducts();
    @Query(value = "select * from product where product_status ='out_of_stock'",nativeQuery = true)
    List<Product>getAllOutOfStockProducts();

    @Query(value = "select * from product where quantity > 100",nativeQuery = true)
    List<Product>getAllProductsGreaterThan100Quantity();

    @Query(value = "select * from product where product_category = :category order by price asc limit 1",nativeQuery = true)
    Product getCheapestProductOfParticularCategory(String category);

    @Query(value = "select * from product where product_category = :category order by price desc limit 1",nativeQuery = true)
    Product getCostliestProductOfParticularCategory(String category);
}
