package com.java.microservices.currencyexchangeservice.controller;

import com.java.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.java.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CurrencyExchangeController {

    private static Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;


    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> currencyExchange(@PathVariable String from, @PathVariable String to) {
        Integer port = Integer.parseInt(environment.getProperty("server.port"));
         CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
         currencyExchange.setPort(port);
         logger.info("{}", currencyExchange);
        return ResponseEntity.ok(currencyExchange);
    }
}
