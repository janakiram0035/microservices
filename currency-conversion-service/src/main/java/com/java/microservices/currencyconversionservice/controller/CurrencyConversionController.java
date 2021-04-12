package com.java.microservices.currencyconversionservice.controller;

import com.java.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import com.java.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyConversionController {

    private static Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @Autowired
    private Environment environment;

    /*
    @Autowired
    private CurrencyExchangeZuulProxy currencyExchangeZuulProxy;*/


    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionBean> currencyConversionBean(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        Map<String, String> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);
        ResponseEntity<CurrencyConversionBean> currencyConversionBeanResponseEntity = restTemplate.getForEntity("http://localhost:8000/api/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, map);

        CurrencyConversionBean c = currencyConversionBeanResponseEntity.getBody();
        c.setQuantity(quantity);
        c.setTotalCalculatedAmount(c.getConversionMultiple().multiply(quantity));
        c.setCurrencyConversionPort(Integer.parseInt(environment.getProperty("server.port")));
        return ResponseEntity.ok(c);

    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionBean> currencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        CurrencyConversionBean currencyConversionBean = currencyExchangeProxy.currencyExchange(from, to);
        currencyConversionBean.setQuantity(quantity);
        currencyConversionBean.setTotalCalculatedAmount(currencyConversionBean.getConversionMultiple().multiply(quantity));
        currencyConversionBean.setCurrencyConversionPort(Integer.parseInt(environment.getProperty("server.port")));

        return ResponseEntity.ok(currencyConversionBean);

    }

    //with zuul
   /* @GetMapping("/currency-converter-zuul/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversionZuul(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean();
        try {
            currencyConversionBean = currencyExchangeZuulProxy.currencyExchange(from, to);
        } catch (Exception e) {
            e.printStackTrace();
        }
        currencyConversionBean.setQuantity(quantity);
        if (null != currencyConversionBean.getConversionMultiple()) {
            currencyConversionBean.setTotalCalculatedAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()));
        }
        logger.info(" zuul reached {}", currencyConversionBean);

        return currencyConversionBean;

    }*/
}
