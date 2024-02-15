package com.imon.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeBuilder) {
		return routeBuilder.routes()
				.route(p -> p
						.path("/quiz/**")
						.uri("lb://quiz-service"))
				.route(p -> p
						.path("/question/**")
						.uri("lb://question-service"))
				.build();
	}
}
