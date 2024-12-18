package br.com.gravitech.condonews.repository;

import br.com.gravitech.condonews.domain.News;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface NewsRepository extends MongoRepository<News, UUID> {

    List<News> findByBreakingIsTrue();
}
