package com.yerinden.yerinden.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String vkn;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Long reviewCount;
    private Double reviewPoint;

    @OneToMany
    private List<User> marketAdmins;
    @OneToMany
    private List<Product> sellerProducts;
    @OneToMany
    private List<SellerTransaction> sellerTransactions;
    @OneToMany
    private List<MarketFollower> marketFollowers;

}
