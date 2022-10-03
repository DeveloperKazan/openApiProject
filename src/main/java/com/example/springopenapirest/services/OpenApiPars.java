package com.example.springopenapirest.services;

import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiPars {

    @Value("${api.version}") String apiVersion;

    ParseOptions options = new ParseOptions();

//    @Bean
//    public void generateApiVersion(){
//        options.setResolve(true); // implicit
//        options.setResolveFully(true);
//        options.setResolveCombinators(false);
//
//        OpenAPI openAPI = new OpenAPIV3Parser().read("src/main/resources/static/api-docs.yaml", null, options);
//        openAPI.getInfo()
//                .setVersion(apiVersion);
//
//
//    }
}
