package com.argus.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy //开启zuul
@SpringBootApplication
public class ZuulGatewayApplication  extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ZuulGatewayApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}

}
