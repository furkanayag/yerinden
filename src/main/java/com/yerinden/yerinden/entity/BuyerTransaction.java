package com.yerinden.yerinden.entity;

import com.yerinden.yerinden.model.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToMany
    @JoinColumn(name = "productId")
    private List<Product> products;
}
