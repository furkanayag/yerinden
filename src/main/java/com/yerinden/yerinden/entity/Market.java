package com.yerinden.yerinden.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String vkn;
    private String name;
    private String email;
    private String phone;
    private String address;

    @Builder.Default
    private Long reviewCount = 0L;

    @Builder.Default
    private Double reviewPoint = 0D;

    private Boolean isActive;

    @OneToMany
    private List<User> marketAdmins;
    @OneToMany
    private List<Product> sellerProducts;
    @OneToMany
    private List<SellerTransaction> sellerTransactions;
    @OneToMany
    private List<MarketFollower> marketFollowers;

}
