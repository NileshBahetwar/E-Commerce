package com.example.eccomerceproject.repository;

import com.example.eccomerceproject.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
     Card findByCardNo(String cardNo);
     @Query(value = "select * from card where card_type = 'VISA'",nativeQuery = true)
     List<Card> findAllByCardType();
}
