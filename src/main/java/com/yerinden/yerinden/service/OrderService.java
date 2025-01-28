package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.entity.Product;
import com.yerinden.yerinden.entity.Transaction;
import com.yerinden.yerinden.entity.User;
import com.yerinden.yerinden.repository.TransactionRepository;
import com.yerinden.yerinden.security.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final UserService userService;
    private final BasketService basketService;
    private final TransactionRepository transactionRepository;

    public EmptyResponse order(UserSession userSession){
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        List<Product> basketProducts = basketService.getBasketProducts(userSession).getProducts();
        Double transactionAmount = basketProducts.stream().map(Product::getPrice).reduce(0.0, Double::sum);
        Transaction transaction = Transaction.builder()
                .amount(transactionAmount)
                .products(basketProducts)
                .user(user)
                .build();
        transactionRepository.save(transaction);
        //todo enter payment flow
        return new EmptyResponse();
    }
}
