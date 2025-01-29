package com.yerinden.yerinden.entity;

import com.yerinden.yerinden.model.Gender;
import com.yerinden.yerinden.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //common fields
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isActive;
    private Boolean isKKKApproved;
    private Boolean isCommercialsApproved;
    private Boolean isEmailVerified;

    //buyer fields
    private String address;

    @OneToMany
    private List<BasketItem> basketProducts;
    @OneToMany
    private List<BuyerTransaction> buyerBuyerTransactions;
    @OneToMany
    private List<Market> followedMarkets;

    //seller fields
    @ManyToOne
    @JoinColumn(name = "marketId")
    private Market market;

}
