package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.BasketAddRequest;
import com.yerinden.yerinden.controller.response.BasketProductsResponse;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.FollowedMarketResponse;
import com.yerinden.yerinden.entity.*;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.repository.BasketItemRepository;
import com.yerinden.yerinden.repository.MarketFollowerRepository;
import com.yerinden.yerinden.security.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarketFollowService {

    private final UserService userService;
    private final MarketService marketService;
    private final MarketFollowerRepository repository;

    public FollowedMarketResponse getFollowedMarkets(UserSession userSession){
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        List<MarketFollower> items = getBasketItems(user);
        List<Market> markets = items.stream().map(MarketFollower::getMarket).toList();
        return new FollowedMarketResponse(markets);
    }

    public EmptyResponse addItem(UserSession userSession, BasketAddRequest request){
        Market market = marketService.findById(request.getProductId());
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        MarketFollower marketFollower = MarketFollower.builder().market(market).user(user).build();
        repository.save(marketFollower);
        return new EmptyResponse();
    }

    public EmptyResponse deleteItem(UserSession userSession, BasketAddRequest request){
        Market market = marketService.findById(request.getProductId());
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        MarketFollower marketFollower = findItemByUserAndProduct(user, market);
        deleteBasketItem(marketFollower);
        return new EmptyResponse();
    }

    private MarketFollower findItemByUserAndProduct(User user, Market market) throws BusinessException {
        return repository.findByUserAndMarket(user, market)
                .orElseThrow(BusinessException::basketItemNotFound);
    }

    private EmptyResponse deleteBasketItem(MarketFollower marketFollower){
        repository.delete(marketFollower);
        return new EmptyResponse();
    }

    private List<MarketFollower> getBasketItems(User user){ return repository.findAllByUser(user); }
}
