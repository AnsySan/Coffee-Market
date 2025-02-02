package com.ansysan.coffeemarket.security.api;

import com.ansysan.coffeemarket.openapi.dto.UserDto;
import com.ansysan.coffeemarket.user.converter.UserDtoConverter;
import com.ansysan.coffeemarket.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityPrincipalProvider {

    private final UserDtoConverter userDtoConverter;

    public SecurityPrincipalProvider(UserDtoConverter userDtoConverter) {
        this.userDtoConverter = userDtoConverter;
    }

    public UserDto get() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return userDtoConverter.toDto(userEntity);
    }

    public UUID getUserId() {
        return get().getId();
    }
}