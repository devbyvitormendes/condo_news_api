package br.com.gravitech.condonews.repository;

import br.com.gravitech.condonews.domain.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ResidentRepository extends MongoRepository<Resident, UUID> {
}
