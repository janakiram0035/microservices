package com.java.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class CircuitBreakerController {

    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")

    @Retry(name = "sample-api", fallbackMethod = "hardCodeValues")
    public ResponseEntity<String> sampleApi() {

        logger.info("Request received for sample api call");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8100/sample", String.class);
        return new ResponseEntity<>( responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/sample-circuit-breaker")
   // @CircuitBreaker(name = "default", fallbackMethod = "hardCodeValues")
    //@RateLimiter(name="default")
    @Bulkhead(name="default")
    public ResponseEntity<String> sampleCircuitBreaker() {
        logger.info("Request received for sample circuit breaker");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8100/sample", String.class);
        return new ResponseEntity<>( responseEntity.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<String> hardCodeValues(Exception e) {

        logger.info("Request received for hardcoded api call {}", e.getMessage());
        return new ResponseEntity<>( "hardCodedValues", HttpStatus.OK);
    }
}
