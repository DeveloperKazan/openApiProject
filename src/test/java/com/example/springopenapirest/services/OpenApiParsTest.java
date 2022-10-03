package com.example.springopenapirest.services;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;
import org.openapitools.codegen.languages.OpenAPIYamlGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PropertySource("classpath:application.properties")
class OpenApiParsTest {

    @Value("${api.version}")
    static String apiVersion;

    static String getApiVersion;

    @BeforeAll
    static void setApiVersion(){
        getApiVersion = apiVersion;
    }

    @Test
    public void testGeneratePing() throws Exception {

//        ParseOptions options = new ParseOptions();
//        options.setResolve(true); // implicit
//        options.setResolveFully(true);
//        options.setResolveCombinators(false);

        Map<String, Object> properties = new HashMap<>();

        OpenAPI openAPI = new OpenAPIV3Parser().read("src/main/resources/static/api-docs.yaml", null, null);
        openAPI.getInfo()
                .setVersion(getApiVersion);
        System.out.println(openAPI);

    }

    @Test
    public void testGeneratePingOtherOutputFile() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put(OpenAPIYamlGenerator.OUTPUT_NAME, "ping.yaml");

        File output = Files.createTempDirectory("test").toFile();

        final CodegenConfigurator configurator = new CodegenConfigurator()
                .setGeneratorName("api-docs.yaml")
                .setAdditionalProperties(properties)
                .setInputSpec("src/main/resources/static/api-docs.yaml")
                .setOutputDir("src/main/resources/static");

        final ClientOptInput clientOptInput = configurator.toClientOptInput();
        DefaultGenerator generator = new DefaultGenerator();
        List<File> files = generator.opts(clientOptInput).generate();
    }
}