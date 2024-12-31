package br.com.gravitech.condonews.config.security;

import br.com.gravitech.condonews.domain.User;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.exception.token.TokenCreationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    public AuthResponseDto generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("gravitech-condonews-api")
                    .withIssuedAt(new Date())
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                    .sign(algorithm);

            return new AuthResponseDto(token, null, getExpiration(token), user.getIdCondo());
        } catch (JWTCreationException ex) {
            log.error("[ERROR] Error creating token: {}", ex.getMessage());
            throw new TokenCreationException();
        }
    }

    private long getExpiration(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getExpiresAt().toInstant().toEpochMilli();
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("gravitech-condonews-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception ex) {
            log.error("[ERROR] Error validating token: {}", ex.getMessage());
            return null;
        }
    }
}
