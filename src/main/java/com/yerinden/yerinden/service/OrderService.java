package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.UpdateOrderStatusObject;
import com.yerinden.yerinden.controller.request.UpdateOrderStatusRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.BuyerTransactionHistoryResponse;
import com.yerinden.yerinden.controller.response.SellerTransactionHistoryResponse;
import com.yerinden.yerinden.entity.*;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.model.OrderStatus;
import com.yerinden.yerinden.repository.BuyerTransactionRepository;
import com.yerinden.yerinden.repository.SellerTransactionRepository;
import com.yerinden.yerinden.security.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final UserService userService;
    private final BasketService basketService;
    private final BuyerTransactionRepository buyerTransactionRepository;
    private final SellerTransactionRepository sellerTransactionRepository;

    public BuyerTransactionHistoryResponse getBuyerOrders(UserSession userSession){
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        List<BuyerTransaction> buyerTransactions = buyerTransactionRepository.findAllByUser(user);
        return new BuyerTransactionHistoryResponse(buyerTransactions);
    }

    public SellerTransactionHistoryResponse getSellerOrders(UserSession userSession){
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        checkIfMarketExist(user);
        List<SellerTransaction> sellerTransactions = sellerTransactionRepository.findAllByMarket(user.getMarket());
        return new SellerTransactionHistoryResponse(sellerTransactions);
    }

    @Transactional
    public EmptyResponse order(UserSession userSession){
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        List<Product> basketProducts = basketService.getBasketProducts(userSession).getProducts();

        Double transactionAmount = basketProducts.stream().map(Product::getPrice).reduce(0.0, Double::sum);
        buyerTransactionRepository.save(createBuyerTransaction(user, transactionAmount, basketProducts));

        basketProducts.forEach(product -> createSellerTransaction(product, user));
        //todo enter payment flow
        return new EmptyResponse();
    }

    @Transactional
    public EmptyResponse updateOrderStatus(UpdateOrderStatusRequest request){
        request.getUpdateOrderStatusObjects().forEach(this::updateSellerTransactionStatus);
        return new EmptyResponse();
    }

    public BuyerTransaction findBuyerTransaction(Long id){
        return buyerTransactionRepository.findById(id).orElseThrow(BusinessException::transactionNotFound);
    }

    public SellerTransaction findSellerTransaction(Long id){
        return sellerTransactionRepository.findById(id).orElseThrow(BusinessException::transactionNotFound);
    }

    private BuyerTransaction createBuyerTransaction(User user, Double transactionAmount, List<Product> products){
        return BuyerTransaction.builder()
                .amount(transactionAmount)
                .products(products)
                .user(user)
                .build();
    }

    private SellerTransaction createSellerTransaction(Product product, User user){
        return SellerTransaction.builder()
                .market(product.getMarket())
                .amount(product.getPrice())
                .user(user)
                .status(OrderStatus.INITIAL)
                .build();
    }

    private void updateSellerTransactionStatus(UpdateOrderStatusObject object){
        SellerTransaction transaction = sellerTransactionRepository.findById(object.getSellerTransactionId())
                .orElseThrow(BusinessException::transactionNotFound);
        transaction.setStatus(object.getOrderStatus());
        sellerTransactionRepository.save(transaction);
    }

    private void checkIfMarketExist(User user){ if(user.getMarket() == null){throw BusinessException.marketNotFound();}}
}
