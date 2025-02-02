package com.ansysan.coffeemarket.security.api;

import com.ansysan.coffeemarket.openapi.dto.UserRegistrationRequest;
import com.ansysan.coffeemarket.openapi.dto.UserRegistrationResponse;
import com.ansysan.coffeemarket.security.converter.RegistrationDtoConverter;
import com.ansysan.coffeemarket.security.jwt.JwtTokenProvider;
import com.ansysan.coffeemarket.user.entity.Authority;
import com.ansysan.coffeemarket.user.entity.UserEntity;
import com.ansysan.coffeemarket.user.entity.UserGrantedAuthority;
import com.ansysan.coffeemarket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private static final boolean DEFAULT_ACCOUNT_NON_EXPIRED = true;
    private static final boolean DEFAULT_ACCOUNT_NON_LOCKED = true;
    private static final boolean DEFAULT_CREDENTIALS_NON_EXPIRED = true;
    private static final boolean DEFAULT_ENABLED = true;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userCrudRepository;
    private final RegistrationDtoConverter registrationDtoConverter;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationResponse register(final UserRegistrationRequest userRegistrationRequest) {
        String encryptedPassword = passwordEncoder.encode(userRegistrationRequest.getPassword());
        UserGrantedAuthority defaultUserGrantedAuthority = UserGrantedAuthority.builder().authority(Authority.USER).build();

        UserEntity newUserEntity = registrationDtoConverter.toEntity(userRegistrationRequest);
        newUserEntity.setPassword(encryptedPassword);
        newUserEntity.addAuthority(defaultUserGrantedAuthority);
        newUserEntity.setAccountNonExpired(DEFAULT_ACCOUNT_NON_EXPIRED);
        newUserEntity.setAccountNonLocked(DEFAULT_ACCOUNT_NON_LOCKED);
        newUserEntity.setCredentialsNonExpired(DEFAULT_CREDENTIALS_NON_EXPIRED);
        newUserEntity.setEnabled(DEFAULT_ENABLED);

        UserEntity userEntity = userCrudRepository.save(newUserEntity);

        final String jwtRefreshToken = jwtTokenProvider.generateRefreshToken(userEntity);
        final String jwtToken = jwtTokenProvider.generateToken(userEntity);

        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setToken(jwtToken);
        response.setRefreshToken(jwtRefreshToken);
        return response;
    }
}
