package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.BasketAddRequest;
import com.yerinden.yerinden.controller.response.BasketProductsResponse;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.entity.BasketItem;
import com.yerinden.yerinden.entity.Product;
import com.yerinden.yerinden.entity.User;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.repository.BasketItemRepository;
import com.yerinden.yerinden.security.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketService {

    private final ProductService productService;
    private final UserService userService;
    private final BasketItemRepository repository;

    public BasketProductsResponse getBasketProducts(UserSession userSession){
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        List<BasketItem> items = getBasketItems(user);
        List<Product> products = items.stream().map(BasketItem::getProduct).toList();
        return new BasketProductsResponse(products);
    }

    @Transactional
    public EmptyResponse addItem(UserSession userSession, BasketAddRequest request){
        Product product = productService.findById(request.getProductId());
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        BasketItem basketItem = BasketItem.builder().product(product).user(user).build();
        repository.save(basketItem);
        return new EmptyResponse();
    }

    @Transactional
    public EmptyResponse deleteItem(UserSession userSession, BasketAddRequest request){
        Product product = productService.findById(request.getProductId());
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        BasketItem basketItem = findItemByUserAndProduct(user, product);
        deleteBasketItem(basketItem);
        return new EmptyResponse();
    }

    private BasketItem findItemByUserAndProduct(User user, Product product) throws BusinessException {
        return repository.findBasketItemByUserAndProduct(user, product)
                .orElseThrow(BusinessException::basketItemNotFound);
    }

    private void deleteBasketItem(BasketItem basketItem){ repository.delete(basketItem); }

    private List<BasketItem> getBasketItems(User user){ return repository.findAllByUser(user); }
}
