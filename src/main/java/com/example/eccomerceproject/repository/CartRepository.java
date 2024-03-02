package com.example.eccomerceproject.repository;

import com.example.eccomerceproject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
