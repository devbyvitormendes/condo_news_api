package br.com.gravitech.condonews.repository;

import br.com.gravitech.condonews.domain.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserIdAndRevokedFalse(UUID userId);
    void deleteByUserId(UUID userId);
}
