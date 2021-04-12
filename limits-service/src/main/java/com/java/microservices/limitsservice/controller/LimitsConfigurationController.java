package com.java.microservices.limitsservice.controller;

import com.java.microservices.limitsservice.bean.LimitConfiguration;
import com.java.microservices.limitsservice.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    Configuration configuration;

    @GetMapping("/limits")
    public ResponseEntity<LimitConfiguration> getLimits() {


      //  HashMap<String, String> codenames = new HashMap<String, String>();
       // Collections.sort(new ArrayList<>(codenames.entrySet()), Comparator.comparing(Map.Entry::getValue));

        return  ResponseEntity.ok(new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum()));
    }

}
