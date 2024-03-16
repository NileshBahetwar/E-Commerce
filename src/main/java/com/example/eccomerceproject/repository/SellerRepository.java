package com.example.eccomerceproject.repository;

import com.example.eccomerceproject.model.Product;
import com.example.eccomerceproject.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmailId(String s);

//    Seller findById(int id);
    @Query(value = "select * from seller s where s.age > :minAge and s.age < :maxAge",nativeQuery = true)
    List<Seller>getAllSellerWithAge(int minAge,int maxAge);
}
