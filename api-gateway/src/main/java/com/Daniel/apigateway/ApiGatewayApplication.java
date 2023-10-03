package com.Daniel.apigateway;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/UserMicroservice/**")
						.filters(f -> f.rewritePath("/UserMicroservice/(?<remains>.*)", "/${remains}"))
						.uri("lb://USERMICROSERVICE/"))
				.route(r -> r.path("/ControladorUserMicroservice/**")
						.filters(f -> f.rewritePath("/ControladorUserMicroservice/(?<remains>.*)", "/${remains}"))
						.uri("lb://CONTROLADORUSERMICROSERVICE/"))
				.build();
	}
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}
}
