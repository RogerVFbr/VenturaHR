package com.soundlab.dockerizedjavaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Import;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.TimeZone;

@SpringBootApplication
@Configuration
@EnableSwagger2WebMvc
public class DockerizedJavaApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(DockerizedJavaApiApplication.class, args);
    }

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.soundlab.dockerizedjavaapi.controllers"))
            .paths(PathSelectors.any())
            .build()
            .pathProvider(new PathProvider() {
                @Override
                public String getOperationPath(String operationPath) {
                    return operationPath.replace("/api", "");
                }

                @Override
                public String getResourceListingPath(String groupName, String apiDeclaration) {
                    return "/api";
                }
            })
            .apiInfo(apiInfo())
            .directModelSubstitute(Timestamp.class, String.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("SceneManager")
            .description("Esta API tem como objetivo atender ao requisitos do teste técnico para " +
                "desenvolvedor back-end da TV Globo.\n\n Toda informação temporal (data de atualização da cena) a ser " +
                    "consumida ou enviada para a API deve estar \ncom o fuso corrigido para GMT. Entende-se que " +
                    "tais operações devem ser realizadas no cliente.")
            .build();
    }
}
