package com.ansysan.coffeemarket.user.api;

import com.ansysan.coffeemarket.security.api.SecurityPrincipalProvider;
import com.ansysan.coffeemarket.user.converter.AddressDtoConverter;
import com.ansysan.coffeemarket.user.converter.UserDtoConverter;
import com.ansysan.coffeemarket.openapi.dto.AddressDto;
import com.ansysan.coffeemarket.openapi.dto.UpdateUserAccountRequest;
import com.ansysan.coffeemarket.openapi.dto.UserDto;
import com.ansysan.coffeemarket.user.entity.Address;
import com.ansysan.coffeemarket.user.entity.UserEntity;
import com.ansysan.coffeemarket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateUserOperationPerformer {

    private final SingleUserProvider singleUserProvider;
    private final UserRepository userCrudRepository;
    private final UserDtoConverter userDtoConverter;
    private final AddressDtoConverter addressDtoConverter;
    private final SecurityPrincipalProvider securityPrincipalProvider;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public UserDto updateUser(final UpdateUserAccountRequest updateUserAccountRequest) {
        UUID userId = securityPrincipalProvider.getUserId();

        UserEntity userEntity = singleUserProvider.getUserEntityById(userId);

        AddressDto addressDto = updateUserAccountRequest.getAddress();
        Address addressEntity = addressDtoConverter.toEntity(addressDto);

        userEntity.setFirstName(updateUserAccountRequest.getFirstName());
        userEntity.setLastName(updateUserAccountRequest.getLastName());
        userEntity.setBirthDate(updateUserAccountRequest.getBirthDate());
        userEntity.setPhoneNumber(updateUserAccountRequest.getPhoneNumber());
        userEntity.setAddress(addressEntity);

        UserEntity userEntityWithId = userCrudRepository.save(userEntity);
        return userDtoConverter.toDto(userEntityWithId);
    }
}