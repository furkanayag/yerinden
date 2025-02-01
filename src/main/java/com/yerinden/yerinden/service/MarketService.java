package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.AddMarketRequest;
import com.yerinden.yerinden.controller.request.DeleteMarketRequest;
import com.yerinden.yerinden.controller.request.UpdateMarketRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.MarketResponse;
import com.yerinden.yerinden.controller.response.MarketsResponse;
import com.yerinden.yerinden.entity.Market;
import com.yerinden.yerinden.entity.User;
import com.yerinden.yerinden.mapper.EntityMapper;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarketService {

    private final MarketRepository repository;
    private final UserService userService;
    private final EntityMapper entityMapper;

    public MarketsResponse findAllActiveMarkets(){ return new MarketsResponse(repository.findAllByIsActive(true)); }

    public MarketResponse findMarket(Long id) { return new MarketResponse(findById(id)); }

    public EmptyResponse addMarket(AddMarketRequest request){
        Market market = Market.builder()
                .vkn(request.getVkn())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .isActive(request.getIsActive())
                .marketAdmins(findAdminUsersById(request.getMarketAdminUserIds()))
                .build();
        saveMarket(market);
        return new EmptyResponse();
    }

    public EmptyResponse updateMarket(UpdateMarketRequest request){
        Market market = findById(request.getMarketId());
        entityMapper.mergeMarket(market, request);
        market.setMarketAdmins(findAdminUsersById(request.getMarketAdminUserIds()));
        return new EmptyResponse();
    }

    public EmptyResponse deleteMarket(DeleteMarketRequest request){
        Market market = findById(request.getMarketId());
        deleteMarket(market);
        return new EmptyResponse();
    }

    private List<User> findAdminUsersById(List<Long> ids){
        List<User> admins = new ArrayList<>();
        ids.forEach(id -> {
            try {
                User user = userService.findByIdAndIsActive(id);
                admins.add(user);
            } catch (BusinessException exception) {
                log.info("User not found for user id: {}", id);
                log.error(exception.getMessage());
            }
        });
        return admins;
    }

    public Market findById(Long marketId) throws BusinessException { return repository.findById(marketId).orElseThrow(BusinessException::marketNotFound); }

    public void saveMarket(Market market){ repository.save(market); }

    private void deleteMarket(Market market){ repository.delete(market); }
}
