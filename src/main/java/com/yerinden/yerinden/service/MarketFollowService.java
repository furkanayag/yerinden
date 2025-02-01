package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.FollowMarketRequest;
import com.yerinden.yerinden.controller.request.RemoveFollowedMarketRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.FollowedMarketResponse;
import com.yerinden.yerinden.entity.Market;
import com.yerinden.yerinden.entity.MarketFollower;
import com.yerinden.yerinden.entity.User;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.repository.MarketFollowerRepository;
import com.yerinden.yerinden.security.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public EmptyResponse addItem(UserSession userSession, FollowMarketRequest request){
        Market market = marketService.findById(request.getMarketId());
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        MarketFollower marketFollower = MarketFollower.builder().market(market).user(user).build();
        repository.save(marketFollower);
        return new EmptyResponse();
    }

    @Transactional
    public EmptyResponse deleteItem(UserSession userSession, RemoveFollowedMarketRequest request){
        Market market = marketService.findById(request.getMarketId());
        User user = userService.findByEmailAndIsActive(userSession.getEmail());
        MarketFollower marketFollower = findItemByUserAndProduct(user, market);
        deleteBasketItem(marketFollower);
        return new EmptyResponse();
    }

    private MarketFollower findItemByUserAndProduct(User user, Market market) throws BusinessException {
        return repository.findByUserAndMarket(user, market)
                .orElseThrow(BusinessException::basketItemNotFound);
    }

    private void deleteBasketItem(MarketFollower marketFollower){ repository.delete(marketFollower); }

    private List<MarketFollower> getBasketItems(User user){ return repository.findAllByUser(user); }
}
