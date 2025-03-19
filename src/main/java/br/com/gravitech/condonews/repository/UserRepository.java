package br.com.gravitech.condonews.repository;

import br.com.gravitech.condonews.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    @Query(value = "{ 'idCondo': ?0, 'active': true }")
    long countByIdCondoAndActiveTrue(UUID idCondo);
}
