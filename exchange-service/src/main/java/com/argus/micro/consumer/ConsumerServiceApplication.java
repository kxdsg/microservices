package com.argus.micro.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient //添加发现服务的能力
@SpringBootApplication
public class ConsumerServiceApplication extends SpringBootServletInitializer {


	@Bean
	@LoadBalanced //开启负载均衡
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ConsumerServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceApplication.class, args);
	}
}
