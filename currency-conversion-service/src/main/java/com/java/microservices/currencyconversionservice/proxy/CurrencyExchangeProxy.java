package com.java.microservices.currencyconversionservice.proxy;

import com.java.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("/api/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean currencyExchange(@PathVariable String from, @PathVariable String to);

   /* @GetMapping("/currency-exchange-service/from/{from}/to/{to}")
    CurrencyConversionBean currencyExchange(@PathVariable String from, @PathVariable String to);

    @GetMapping("/currency-exchange-service")
    String currencyExchange1(@RequestParam(value = "from") String from);

  @PostMapping("/currency-exchange-service")
  String currencyExchange2(@RequestBody CurrencyConversionBean body);*/
}
