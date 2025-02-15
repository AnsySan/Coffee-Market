package com.ansysan.coffeemarket.user.api;

import com.ansysan.coffeemarket.user.avatar.UserAvatarLinkProvider;
import com.ansysan.coffeemarket.openapi.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAvatarLinkUpdater {

    private final UserAvatarLinkProvider userAvatarLinkProvider;

    public UserDto update(final UserDto userDto) {
        final UUID userId = userDto.getId();
        String userAvatarLink = null;
        try {
            userAvatarLink = userAvatarLinkProvider.getLink(userId);
        } catch (Exception exception) {
            log.error("FileProvider error", exception);
        }
        userDto.setAvatarLink(userAvatarLink);
        return userDto;
    }
}