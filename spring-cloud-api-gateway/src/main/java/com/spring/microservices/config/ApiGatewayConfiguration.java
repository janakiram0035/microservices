package com.spring.microservices.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {


    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        Function<PredicateSpec, Buildable<Route>> ro = p -> p.path("/get")
                .filters(f -> f.addRequestHeader("hello", "heelo")
                        .addRequestParameter("param", "param")
                )
                .uri("http://httpbin.org:80");
        return routeLocatorBuilder
                .routes()
                .route(ro)
                .route( p -> p.path("/api/currency-exchange/**")
                        .uri("lb://currency-exchange-service"))
                .route(p -> p.path("/api/currency-conversion/**")
                        .uri("lb://currency-conversion-service"))
                .route(p-> p.path("/api/currency-conversion-feign/**")
                        .uri("lb://currency-conversion-service"))
                .route(p -> p.path("/api/currency-conversion-new/**")
                        .filters( f -> f.rewritePath("/api/currency-conversion-new/(?<segment>.*)" , "/api/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion-service"))
                .build();
    }
}
