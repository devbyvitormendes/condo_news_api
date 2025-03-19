package br.com.gravitech.condonews.config.security;

import br.com.gravitech.condonews.domain.RefreshToken;
import br.com.gravitech.condonews.domain.User;
import br.com.gravitech.condonews.domain.utils.StringConstants;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.exception.token.TokenCreationException;
import br.com.gravitech.condonews.exception.token.TokenRefreshException;
import br.com.gravitech.condonews.repository.RefreshTokenRepository;
import br.com.gravitech.condonews.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final HttpServletResponse response;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.refresh-token.expiration:604800000}")
    private Long refreshTokenExpiration; // Default 7 days

    public AuthResponseDto generateToken(User user) {
        try {
            log.info(StringConstants.Log.STARTING_METHOD, "generateToken");
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("gravitech-condonews-api")
                    .withIssuedAt(new Date())
                    .withSubject(user.getUsername())
                    .withClaim("userId", user.getId().toString())
                    .withClaim("condoId", user.getIdCondo().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                    .sign(algorithm);

            String refreshToken = createRefreshToken(user);
            setRefreshTokenCookie(refreshToken);

            log.info(StringConstants.Log.TOKEN_GENERATED, user.getUsername());
            log.info(StringConstants.Log.ENDING_METHOD, "generateToken");
            return new AuthResponseDto(token, refreshToken, getExpiration(token), user.getIdCondo());
        } catch (JWTCreationException ex) {
            log.error(StringConstants.Log.Error.ERROR_CREATING + ": {}", "token", ex.getMessage());
            throw new TokenCreationException();
        }
    }

    private String createRefreshToken(User user) {
        log.info(StringConstants.Log.STARTING_METHOD, "createRefreshToken");
        // Revoke any existing refresh tokens for this user
        Optional<RefreshToken> existingToken = refreshTokenRepository.findByUserIdAndRevokedFalse(user.getId());
        existingToken.ifPresent(token -> {
            token.setRevoked(true);
            refreshTokenRepository.save(token);
            log.info(StringConstants.Log.Auth.LOGGING_OUT);
        });

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setId(UUID.randomUUID());
        refreshToken.setUserId(user.getId());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenExpiration));
        refreshToken.setRevoked(false);

        refreshTokenRepository.save(refreshToken);
        log.info(StringConstants.Log.TOKEN_GENERATED, user.getUsername());
        log.info(StringConstants.Log.ENDING_METHOD, "createRefreshToken");
        return refreshToken.getToken();
    }

    private void setRefreshTokenCookie(String refreshToken) {
        log.info(StringConstants.Log.STARTING_METHOD, "setRefreshTokenCookie");
        Cookie cookie = new Cookie(StringConstants.Security.REFRESH_TOKEN_COOKIE, refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath(StringConstants.Api.AUTH_PATH);
        cookie.setMaxAge((int) (refreshTokenExpiration / 1000));
        response.setHeader("Set-Cookie", String.format("%s; SameSite=Strict", cookie.toString()));
        response.addCookie(cookie);
        log.info(StringConstants.Log.ENDING_METHOD, "setRefreshTokenCookie");
    }

    private void clearRefreshTokenCookie() {
        log.info(StringConstants.Log.STARTING_METHOD, "clearRefreshTokenCookie");
        Cookie cookie = new Cookie(StringConstants.Security.REFRESH_TOKEN_COOKIE, "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath(StringConstants.Api.AUTH_PATH);
        cookie.setMaxAge(0);
        response.setHeader("Set-Cookie", String.format("%s; SameSite=Strict", cookie.toString()));
        response.addCookie(cookie);
        log.info(StringConstants.Log.ENDING_METHOD, "clearRefreshTokenCookie");
    }

    public AuthResponseDto refreshAccessToken(String refreshToken) {
        log.info(StringConstants.Log.Auth.REFRESHING_TOKEN, refreshToken);
        return refreshTokenRepository.findByToken(refreshToken)
                .filter(token -> !token.isRevoked() && token.getExpiryDate().isAfter(Instant.now()))
                .map(token -> {
                    User user = userRepository.findById(token.getUserId())
                            .orElseThrow(() -> new TokenRefreshException(StringConstants.Exception.USER_NOT_FOUND));
                    log.info(StringConstants.Log.USER_AUTHENTICATED, user.getUsername());
                    return generateToken(user);
                })
                .orElseThrow(() -> new TokenRefreshException(StringConstants.Exception.INVALID_TOKEN));
    }

    public void revokeRefreshToken(String refreshToken) {
        log.info(StringConstants.Log.Auth.LOGGING_OUT);
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new TokenRefreshException(StringConstants.Exception.INVALID_TOKEN));
        token.setRevoked(true);
        refreshTokenRepository.save(token);
        clearRefreshTokenCookie();
        log.info(StringConstants.Log.ENDING_METHOD, "revokeRefreshToken");
    }

    private long getExpiration(String token) {
        log.info(StringConstants.Log.STARTING_METHOD, "getExpiration");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        long result = JWT.require(algorithm)
                .build()
                .verify(token)
                .getExpiresAt().toInstant().toEpochMilli();
        log.info(StringConstants.Log.ENDING_METHOD, "getExpiration");
        return result;
    }

    public String validateToken(String token) {
        try {
            log.info(StringConstants.Log.STARTING_METHOD, "validateToken");
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String result = JWT.require(algorithm)
                    .withIssuer("gravitech-condonews-api")
                    .build()
                    .verify(token)
                    .getSubject();
            log.info(StringConstants.Log.ENDING_METHOD, "validateToken");
            return result;
        } catch (Exception ex) {
            log.error(StringConstants.Log.Error.ERROR_FINDING + ": {}", "token", ex.getMessage());
            return null;
        }
    }
}
