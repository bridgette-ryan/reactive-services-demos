package com.bridgetter.customerservice;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}


	@Bean
	public GroupedOpenApi customersOpenApi(@Value("${springdoc.version}") String appVersion) {
		String[] paths = { "/customers/**" } ;
		return GroupedOpenApi.builder()
				.group("customers")
				.addOpenApiCustomizer(openApi -> openApi.info(new Info().title("").version(appVersion)))
				.pathsToMatch(paths)
				.build() ;
	}
}
