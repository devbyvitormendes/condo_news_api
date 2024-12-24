package br.com.gravitech.condonews.config.util;

import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.exception.base.ForbiddenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.expiration-refresh}")
    private long expirationRefresh;

    public AuthResponseDto getToken(String username) {
        if (Objects.nonNull(username)) {
            return new AuthResponseDto(generateToken(username), generateRefreshToken(username));
        } else {
            throw new ForbiddenException();
        }
    }

    public String generateToken(String username) {
        log.info("Generating token for user {}", username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("[ERROR] Expired token: {}", e.getMessage());
            throw new ForbiddenException();
        } catch (Exception e) {
            log.error("[ERROR]Unknown error: {}", e.getMessage());
            throw new ForbiddenException();
        }
    }

    public String extractUserName(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, String username) {
        return (username.equals(extractUserName(token)) && !isTokenExpired(token));
    }

    public String generateRefreshToken(String username) {
        log.info("Generating refresh token for user {}", username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationRefresh))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
