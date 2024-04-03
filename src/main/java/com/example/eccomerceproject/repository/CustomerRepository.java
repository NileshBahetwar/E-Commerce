package com.example.eccomerceproject.repository;

import com.example.eccomerceproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByMobNo(String mobNo);

    Customer findByEmailId(String emailId);
    void deleteByEmailId(String emailId);

    //Customer findByCardNo(String cardNo);
    @Query(value = "select * from customer where age < 25",nativeQuery = true)
    List<Customer> customerBelow25();

//    @Query(value = "select * from customer where card_type = 'VISA'",nativeQuery = true)
//    List<Customer>getVisaUserCustomers();
}
