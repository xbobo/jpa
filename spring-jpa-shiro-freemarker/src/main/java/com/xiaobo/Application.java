package com.xiaobo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.xiaobo")
@EntityScan("com.xiaobo.bean")
@SpringBootApplication
@EnableCaching
public class Application {
	 public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
