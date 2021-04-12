package com.java.microservices.currencyconversionservice.proxy;

//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")
//@FeignClient(name = "currency-exchange-service")
//@FeignClient(name = "netflix-zuul-api-gateway-server")
//@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeZuulProxy {

  /*  @GetMapping("/currency-exchange-service/from/{from}/to/{to}")
    CurrencyConversionBean currencyExchange(@PathVariable String from, @PathVariable String to);
*/
    /*@GetMapping("/currency-exchange-service/currency-exchange-service/from/{from}/to/{to}")
    CurrencyConversionBean currencyExchangewithoutZuulConfiguring(@PathVariable String from, @PathVariable String to);

    //This is if you configure zuul routs like serviceId and path=/currency-exchange/** and serviceId=currency-exchange-service
    @GetMapping("/currency-exchange/currency-exchange-service/from/{from}/to/{to}")
    CurrencyConversionBean currencyExchange(@PathVariable String from, @PathVariable String to);*/
}
