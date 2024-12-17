package br.com.gravitech.condonews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CondonewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CondonewsApplication.class, args);
	}

}
