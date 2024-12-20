package br.com.gravitech.condonews.repository;

import br.com.gravitech.condonews.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> findByUserName(String userName);
}
