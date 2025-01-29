package com.yerinden.yerinden.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;
    private String description;
    private String productCategory; //todo replace it with enum
    private Double price;
    private Long stock;
    private Boolean isAvailable;

    @OneToMany
    private List<BasketItem> basketItems;
    @OneToMany
    private List<User> boughtUsers;
    @ManyToMany
    private List<BuyerTransaction> buyerTransactions;
    @ManyToOne
    @JoinColumn(name = "marketId")
    private Market market;

}
