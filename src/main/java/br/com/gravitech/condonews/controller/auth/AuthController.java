package br.com.gravitech.condonews.controller.auth;

import br.com.gravitech.condonews.domain.utils.StringConstants;
import br.com.gravitech.condonews.dto.auth.AuthRequestDto;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.dto.auth.RefreshRequestDto;
import br.com.gravitech.condonews.service.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController implements AuthApi {

    private final AuthService authService;
    private final HttpServletRequest request;

    @Override
    @PermitAll
    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponseDto createToken(@RequestBody AuthRequestDto request) {
        log.info(StringConstants.Log.STARTING_METHOD, "createToken");
        return authService.authenticate(request);
    }

    @Override
    @PermitAll
    @PostMapping(value = "/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponseDto refreshToken(@RequestBody RefreshRequestDto request) {
        log.info(StringConstants.Log.STARTING_METHOD, "refreshToken");
        return authService.refreshToken(request);
    }

    @Override
    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public void logout() {
        log.info(StringConstants.Log.STARTING_METHOD, "logout");
        getRefreshTokenFromCookie().ifPresent(authService::logout);
    }

    private Optional<String> getRefreshTokenFromCookie() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .filter(cookie -> "refreshToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst();
        }
        return Optional.empty();
    }
}

