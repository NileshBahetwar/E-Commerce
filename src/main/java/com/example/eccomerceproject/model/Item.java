package com.example.eccomerceproject.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Data  // getters,setters,RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // removed name attribute
    int requiredQuantity;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @ManyToOne
    @JoinColumn
    Ordered ordered;

    // OneToOne to OneToMany changes in cardinality ratio
    @ManyToOne
    @JoinColumn
    Product product;
}
