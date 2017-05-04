package com.argus.micro.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xingding on 2017/5/4.
 */
@Service
public class ExchangeService {

    private static final Logger log = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${inner.service.currency}")
    private String serviceName;


    @HystrixCommand(fallbackMethod = "exchangeFallback")
    public Double exchange(String type, double amount){
        //调用提供者服务
        //获取汇率
        String url = "http://"+serviceName+"/currency?type=" + type;
        Double currency = restTemplate.getForEntity(url, Double.class).getBody();
        return currency * amount;
    }

    public Double exchangeFallback(String type, double amount){
        log.warn("Call currency service fail, return default value");
        Double currency = 1.0;
        return currency*amount;
    }
}
