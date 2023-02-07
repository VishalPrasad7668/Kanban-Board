package com.niit.spring_cloud_api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration

public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(predicate1 -> predicate1
                        .path("/authdata/**")
                        .uri("http://localhost:8085"))
                .route(predicate2 -> predicate2
                        .path("/userdata/**")
                        .uri("http://localhost:8081"))
                .route(predicate3 -> predicate3
                        .path("/kanbantask/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
