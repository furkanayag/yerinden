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
    private String marketName;
    private String marketEmail;
    private String marketPhone;
    private String marketAddress;

    @OneToMany
    private List<User> marketAdmins;
    @OneToMany
    private List<Product> sellerProducts;
    @OneToMany
    private List<Transaction> sellerTransactions;
    @OneToMany
    private List<MarketFollower> marketFollowers;

}
