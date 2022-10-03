package com.example.springopenapirest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.languages.JavaClientCodegen;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

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



    @Bean
    public String generateApiVersion(@Value("${api.version}") String appVersion) throws IOException {
        ParseOptions options = new ParseOptions();
        options.setResolveFully(true);
        options.setResolveCombinators(true);

        OpenAPI openAPI = new OpenAPIV3Parser().read("src/main/resources/static/api-docs.yaml", null, options);
        openAPI.getInfo()
                .setVersion(appVersion);

        CodegenConfig config = new JavaClientCodegen();

        config.setOutputDir("src/main/resources/static/api-docs.yaml");

        ClientOptInput clientOptInput = new ClientOptInput();
        clientOptInput.openAPI(openAPI).config(config);
        DefaultGenerator generator = new DefaultGenerator();
        generator.opts(clientOptInput).generate();


//        Path targetDirectory = Paths.get("src/main/resources/static/api-docs.yaml");
//        Files.copy(sourceDirectory, targetDirectory, StandardCopyOption.REPLACE_EXISTING);

        System.out.println(openAPI);

        return openAPI.getInfo().getVersion();

//        Map<String, Object> properties = new HashMap<>();
//        File output = Files.createTempDirectory("test").toFile();
//        final CodegenConfigurator configurator = new CodegenConfigurator()
//                .setGeneratorName("api-docs.yaml")
//                .setAdditionalProperties(properties)
//                .setInputSpec("src/main/resources/static/api-docs.yaml");

//        codegen.setOpenApiSpecFileLocation("src/main/resources/static/api-docs.yaml");
//
//        ClientOptInput input = new ClientOptInput()
//                .openAPI(openAPI)
//                .config(codegen);
//
//        DefaultGenerator generator = new DefaultGenerator(false);
//        generator.opts(input).generate();
    }
}