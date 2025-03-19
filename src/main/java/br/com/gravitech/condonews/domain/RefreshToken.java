package br.com.gravitech.condonews.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@Document(collection = "refresh_tokens")
public class RefreshToken {
    @Id
    private UUID id;
    private String token;
    private UUID userId;
    private Instant expiryDate;
    private boolean revoked;
}
