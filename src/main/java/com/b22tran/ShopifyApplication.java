package com.b22tran;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SpringBootApplication
public class ShopifyApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(ShopifyApplication.class, args);
	}

	@Bean
	public Docket api(){
		@SuppressWarnings("deprecation")
		ApiInfo info = new ApiInfo("Shopify Backend Problem","","1.0.0","","/b22tran",null,null);
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(info)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false);
	}
	
}
