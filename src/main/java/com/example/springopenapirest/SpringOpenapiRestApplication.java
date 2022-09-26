package com.example.springopenapirest;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringOpenapiRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOpenapiRestApplication.class, args);
        }

    @Bean
    public OpenAPI customOpenAPI(@Value("${api.version}") String appVersion) {
        return new OpenAPI().info(new Info()
                .title("Device API")
                .version(appVersion)
                .description("Devices management resource. " +
                        "Demo API")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0")
                        .url("http://springdoc.org")));
    }
}