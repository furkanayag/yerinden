package com.yerinden.yerinden.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToMany
    @JoinColumn(name = "productId")
    private List<Product> products;

}
