package com.ansysan.coffeemarket.user.api;

import com.ansysan.coffeemarket.user.converter.UserDtoConverter;
import com.ansysan.coffeemarket.openapi.dto.UserDto;
import com.ansysan.coffeemarket.user.entity.UserEntity;
import com.ansysan.coffeemarket.user.exception.UserNotFoundException;
import com.ansysan.coffeemarket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SingleUserProvider {

    public static final String LOG_MSG_ON_FAILURE = "Failed to get the user details";

    private final UserRepository userCrudRepository;
    private final UserDtoConverter userDtoConverter;
    private final UserAvatarLinkUpdater userAvatarLinkUpdater;

    @Transactional(readOnly = true)
    public UserDto getUserById(final UUID userId) throws UserNotFoundException {
        return userCrudRepository.findById(userId)
                .map(userDtoConverter::toDto)
                .map(userAvatarLinkUpdater::update)
                .orElseThrow(() -> {
                    log.warn(LOG_MSG_ON_FAILURE);
                    return new UserNotFoundException(userId);
                });
    }

    @Transactional(readOnly = true)
    public UserEntity getUserEntityById(final UUID userId) throws UserNotFoundException {
        return userCrudRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn(LOG_MSG_ON_FAILURE);
                    return new UserNotFoundException(userId);
                });
    }

    @Transactional(readOnly = true)
    public UserDto getUserByEmail(final String email) throws UserNotFoundException {
        return userCrudRepository.findByEmail(email)
                .map(userDtoConverter::toDto)
                .orElseThrow(() -> {
                    log.warn(LOG_MSG_ON_FAILURE);
                    return new UserNotFoundException(null);
                });
    }
}