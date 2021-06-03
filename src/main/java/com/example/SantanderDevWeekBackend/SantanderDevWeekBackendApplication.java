package com.example.SantanderDevWeekBackend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SantanderDevWeekBackendApplication {

	// Executa a aplicação em hospedagem local
	public static void main(String[] args) {
		SpringApplication.run(SantanderDevWeekBackendApplication.class, args);
	}

	// Criação do visualizador do Swagger
	@Bean
	public OpenAPI customOpenAPI (
		@Value("${application.description}") String description,
		@Value("${application.version}") String version
	){
		return new OpenAPI().info(new Info()
				.title(description)
				.version(version)
				.termsOfService("http://swagger.io/terms")
				.license(new License().name("Apache 2.0").url("http://springdoc.org"))
		);
	}
}
