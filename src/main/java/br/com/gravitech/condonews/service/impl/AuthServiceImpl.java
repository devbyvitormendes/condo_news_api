package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.config.security.TokenService;
import br.com.gravitech.condonews.domain.User;
import br.com.gravitech.condonews.domain.utils.StringConstants;
import br.com.gravitech.condonews.dto.auth.AuthRequestDto;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.dto.auth.RefreshRequestDto;
import br.com.gravitech.condonews.exception.base.BadRequestException;
import br.com.gravitech.condonews.service.AuthService;
import br.com.gravitech.condonews.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDto authenticate(AuthRequestDto request) {
        log.info(StringConstants.Log.Auth.AUTHENTICATING_USER, request.username());
        User user = userService.findByUsername(request.username());
        if (!user.isActive()) {
            log.warn(StringConstants.Log.Auth.USER_INACTIVE, request.username());
            throw new BadRequestException(StringConstants.Exception.BAD_REQUEST);
        }
        if (passwordEncoder.matches(request.password(), user.getPassword())) {
            log.info(StringConstants.Log.USER_AUTHENTICATED, user.getUsername());
            var result = tokenService.generateToken(user);
            log.info(StringConstants.Log.ENDING_METHOD, "authenticate");
            return result;
        } else {
            log.warn(StringConstants.Log.Auth.INVALID_CREDENTIALS, request.username());
            throw new BadRequestException(StringConstants.Exception.BAD_CREDENTIALS);
        }
    }

    @Override
    public AuthResponseDto refreshToken(RefreshRequestDto request) {
        log.info(StringConstants.Log.Auth.REFRESHING_TOKEN, request.refreshToken());
        var result = tokenService.refreshAccessToken(request.refreshToken());
        log.info(StringConstants.Log.ENDING_METHOD, "refreshToken");
        return result;
    }

    @Override
    public void logout(String refreshToken) {
        log.info(StringConstants.Log.Auth.LOGGING_OUT);
        tokenService.revokeRefreshToken(refreshToken);
        log.info(StringConstants.Log.ENDING_METHOD, "logout");
    }
}
