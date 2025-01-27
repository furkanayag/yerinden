package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.BasketAddRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.FavProductsResponse;
import com.yerinden.yerinden.entity.FavItem;
import com.yerinden.yerinden.entity.Product;
import com.yerinden.yerinden.entity.User;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.security.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavItemService {

    private final ProductService productService;
    private final UserService userService;
    private final com.yerinden.yerinden.repository.FavItemRepository repository;

    public FavProductsResponse getFavProducts(UserSession userSession){
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        List<FavItem> items = getFavItems(user);
        List<Product> products = items.stream().map(FavItem::getProduct).toList();
        return new FavProductsResponse(products);
    }

    public EmptyResponse addItem(UserSession userSession, BasketAddRequest request){
        Product product = productService.findById(request.getProductId());
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        FavItem favItem = FavItem.builder().product(product).user(user).build();
        repository.save(favItem);
        return new EmptyResponse();
    }

    public EmptyResponse deleteItem(UserSession userSession, BasketAddRequest request){
        Product product = productService.findById(request.getProductId());
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        FavItem favItem = findItemByUserAndProduct(user, product);
        deleteFavItem(favItem);
        return new EmptyResponse();
    }

    private FavItem findItemByUserAndProduct(User user, Product product) throws BusinessException {
        return repository.findByUserAndProduct(user, product)
                .orElseThrow(BusinessException::favItemNotFound);
    }

    private EmptyResponse deleteFavItem(FavItem favItem){
        repository.delete(favItem);
        return new EmptyResponse();
    }

    private List<FavItem> getFavItems(User user){ return repository.findAllByUser(user); }
}
