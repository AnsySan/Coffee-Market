package com.ansysan.coffeemarket.user.api;

import com.ansysan.coffeemarket.user.entity.Authority;
import com.ansysan.coffeemarket.user.entity.UserEntity;
import com.ansysan.coffeemarket.user.entity.UserGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserEntityValuesSetter {

    private static final boolean DEFAULT_ACCOUNT_NON_EXPIRED = true;
    private static final boolean DEFAULT_ACCOUNT_NON_LOCKED = true;
    private static final boolean DEFAULT_CREDENTIALS_NON_EXPIRED = true;
    private static final boolean DEFAULT_ENABLED = true;

    public void setDefaultValues(final UserEntity savedUserEntity) {
        UserGrantedAuthority defaultAuthority = UserGrantedAuthority.builder().authority(Authority.USER).build();
        savedUserEntity.addAuthority(defaultAuthority);
        savedUserEntity.setAccountNonExpired(DEFAULT_ACCOUNT_NON_EXPIRED);
        savedUserEntity.setAccountNonLocked(DEFAULT_ACCOUNT_NON_LOCKED);
        savedUserEntity.setCredentialsNonExpired(DEFAULT_CREDENTIALS_NON_EXPIRED);
        savedUserEntity.setEnabled(DEFAULT_ENABLED);
    }
}