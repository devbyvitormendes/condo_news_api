package br.com.gravitech.condonews.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("CondoNews API")
						.description("CondoNews API is a set of operations used inside our Mobile App.")
						.version("v1.0")
						.termsOfService("http://example.com/terms/")
						.contact(new Contact()
								.name("Gravitech Systems")
								.url("http://www.gravitech.com.br/#contact")
								.email("queroumapp@gravitech.com.br"))
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")));
	}

}
