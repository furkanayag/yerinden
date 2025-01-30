package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.DeleteReviewRequest;
import com.yerinden.yerinden.controller.request.ReviewRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.ReviewResponse;
import com.yerinden.yerinden.entity.*;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.model.ReviewPoint;
import com.yerinden.yerinden.model.ReviewType;
import com.yerinden.yerinden.repository.ReviewRepository;
import com.yerinden.yerinden.security.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository repository;
    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final MarketService marketService;

    public ReviewResponse getReviews(UserSession userSession, ReviewType reviewType, Long id){
        if (reviewType.equals(ReviewType.USER)){ return new ReviewResponse(repository.findAllByUser(userService.findByEmailAndIsActive(userSession.getEmail()))); }
        else if (reviewType.equals(ReviewType.MARKET)) { return new ReviewResponse(repository.findAllByMarket(marketService.findById(id))); }
        else { return new ReviewResponse(repository.findAllByProduct(productService.findById(id))); }
    }

    @Transactional
    public EmptyResponse review(UserSession userSession, ReviewRequest request){
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        SellerTransaction transaction = orderService.findSellerTransaction(request.getTransactionId());
        Product product = productService.findById(request.getProductId());
        checkIfTransactionUserCorrect(user, transaction);
        createReview(request, product, user);
        if (request.getProductReview() != null){
            checkIfProductAlreadyReviewed(user, product);
            updateProductParams(product, request.getProductReview());
        }
        if (request.getMarketReview() != null){
            checkIfMarketAlreadyReviewed(user, product.getMarket());
            updateMarketParams(product.getMarket(), request.getProductReview());
        }
        return new EmptyResponse();
    }

    private void createReview(ReviewRequest request, Product product, User user){
        Review review = Review.builder()
                .product(product)
                .user(user)
                .market(product.getMarket())
                .productReviewPoint(request.getProductReview() == null ? null : request.getProductReview())
                .marketReviewPoint(request.getMarketReview() == null ? null : request.getMarketReview())
                .reviewTextProduct(request.getReviewTextProduct() == null ? null : request.getReviewTextProduct())
                .reviewTextMarket(request.getReviewTextMarket() == null ? null : request.getReviewTextMarket())
                .build();
        repository.save(review);
    }

    @Transactional
    public EmptyResponse delete(UserSession userSession, DeleteReviewRequest request){
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        Review review = findById(request.getReviewId());
        checkIfReviewUserCorrect(user, review);
        deleteReview(review);
        return new EmptyResponse();
    }

    private Review findById(Long id){ return repository.findById(id).orElseThrow(BusinessException::reviewNotFound); }

    private void deleteReview(Review review){ repository.delete(review); }


    private void updateProductParams(Product product, ReviewPoint reviewPoint){
        product.setReviewCount(product.getReviewCount() + 1L);
        product.setReviewPoint(calculateReviewPoint(product.getReviewCount(), product.getReviewPoint(), reviewPoint));
        productService.saveProduct(product);
    }

    private void updateMarketParams(Market market, ReviewPoint reviewPoint){
        market.setReviewCount(market.getReviewCount() + 1L);
        market.setReviewPoint(calculateReviewPoint(market.getReviewCount(), market.getReviewPoint(), reviewPoint));
        marketService.saveMarket(market);
    }

    private Double calculateReviewPoint(Long reviewCount, Double point, ReviewPoint reviewPoint){
        Double reviewCountDouble = (double) reviewCount;
        return ((point * reviewCountDouble) + reviewPoint.getUserPoint()) / reviewCountDouble + 1L;
    }

    private void checkIfTransactionUserCorrect(User user, SellerTransaction sellerTransaction) throws BusinessException {
        if (!sellerTransaction.getUser().equals(user)){throw BusinessException.transactionBelongsOtherUser();}
    }

    private void checkIfProductAlreadyReviewed(User user, Product product) throws BusinessException {
        Optional<Review> reviewHistory = repository.findByUserAndProduct(user, product);
        if (reviewHistory.isPresent()){throw BusinessException.alreadyReviewed();}
    }

    private void checkIfMarketAlreadyReviewed(User user, Market market){
        Optional<Review> reviewHistory = repository.findByUserAndMarket(user, market);
        if (reviewHistory.isPresent()){throw BusinessException.alreadyReviewed();}
    }

    private void checkIfReviewUserCorrect(User user, Review review) throws BusinessException {
        if (!review.getUser().equals(user)){throw BusinessException.reviewBelongOtherUser();}
    }
}
