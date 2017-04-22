package com.argus.micro.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ExchangeController {

    private static final Logger log = LoggerFactory.getLogger(ExchangeController.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${inner.service.currency}")
    private String serviceName;


    @RequestMapping(value = "/exchange", method = RequestMethod.GET)
    public Double exchange(@RequestParam String type, @RequestParam double amount) {
        //调用提供者服务
        //获取汇率
        String url = "http://"+serviceName+"/currency?type=" + type;
        Double currency = restTemplate.getForEntity(url, Double.class).getBody();
        Double result = currency * amount;
        log.info(String.format("exchange result: %s",result));
        return result;
    }

    /**
     * 用于测试ZuulGateway的AuthFilter
     * @param name
     * @return
     */
    @GetMapping("/account")
    public String account(@RequestParam String name){
        log.info("account method");
        return  name.toUpperCase();
    }

    /**
     * 用于测试ZuulGateway的PreHandleFilter
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/head")
    public String head(HttpServletRequest request, HttpServletResponse response){
        String area = request.getHeader("area");
        log.info(String.format("area: %s",area));
        return area;
    }

}
