package com.yerinden.yerinden.mapper;

import com.yerinden.yerinden.controller.request.UserUpdateRequest;
import com.yerinden.yerinden.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EntityMapper {
    void merge(@MappingTarget User user, UserUpdateRequest request);
}
