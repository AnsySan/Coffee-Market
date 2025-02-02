package com.ansysan.coffeemarket.security.converter;

import com.ansysan.coffeemarket.openapi.dto.UserRegistrationRequest;
import com.ansysan.coffeemarket.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegistrationDtoConverter {

    UserEntity toEntity(final UserRegistrationRequest userRegistrationRequest);
}