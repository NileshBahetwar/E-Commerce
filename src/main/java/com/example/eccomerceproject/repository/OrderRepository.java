package com.example.eccomerceproject.repository;

import com.example.eccomerceproject.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ordered,Integer> {
}
