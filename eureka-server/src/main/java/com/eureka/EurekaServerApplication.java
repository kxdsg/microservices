package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //启动服务注册中心，提供给其他应用进行会话
@SpringBootApplication
public class EurekaServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EurekaServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
