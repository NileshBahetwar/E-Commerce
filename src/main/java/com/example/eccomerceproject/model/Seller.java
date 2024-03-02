package com.example.eccomerceproject.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seller")
@NoArgsConstructor
@AllArgsConstructor
@Data  // getters,setters,RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    @Column(unique = true,nullable = false)
    String emailId;
    int age;
    String mobNo;

    @OneToMany(mappedBy = "seller",cascade=CascadeType.ALL)
    List<Product>products=new ArrayList<>();
}
