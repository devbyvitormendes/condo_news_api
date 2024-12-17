package br.com.gravitech.condonews.repository;

import br.com.gravitech.condonews.domain.Condo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CondoRepository extends MongoRepository<Condo, UUID> {
}
