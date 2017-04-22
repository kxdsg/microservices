package com.argus.micro.service.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class CurrencyController {

    private static Map<String,Double> currencyMap = new HashMap<String,Double>();

    static {
        currencyMap.put("SGD",5.1);
        currencyMap.put("USD",6.8);
        currencyMap.put("HKD",0.9);
    }

    private static final Logger logger = Logger.getLogger(CurrencyController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value="/currency", method= RequestMethod.GET)
    public Double add(@RequestParam String type){
        ServiceInstance instance = client.getLocalServiceInstance();
        Double result = currencyMap.get(type);
        if(result==null){
            result = 1.0;
            logger.warn("invalid currency!");
        }
        logger.info("/add, host:" + instance.getHost() + ", port: " + instance.getPort() + ", service_id: " + instance.getServiceId() + " result: " + result);
        return result;
    }
}
