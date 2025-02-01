package com.yerinden.yerinden.mapper;

import com.yerinden.yerinden.controller.request.UpdateMarketRequest;
import com.yerinden.yerinden.controller.request.UpdateUserRequest;
import com.yerinden.yerinden.entity.Market;
import com.yerinden.yerinden.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EntityMapper {
    void mergeUser(@MappingTarget User user, UpdateUserRequest request);
    @Mapping(target = "id", ignore = true)
    void mergeMarket(@MappingTarget Market market, UpdateMarketRequest request);
}
