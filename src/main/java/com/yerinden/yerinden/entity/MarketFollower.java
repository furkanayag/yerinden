package com.yerinden.yerinden.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarketFollower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "marketId")
    private Market market;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
