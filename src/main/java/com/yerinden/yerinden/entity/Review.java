package com.yerinden.yerinden.entity;

import com.yerinden.yerinden.model.ReviewPoint;
import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.error.Mark;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "marketId")
    private Market market;

    private ReviewPoint productReviewPoint;
    private ReviewPoint marketReviewPoint;
    private String reviewTextProduct;
    private String reviewTextMarket;
}
