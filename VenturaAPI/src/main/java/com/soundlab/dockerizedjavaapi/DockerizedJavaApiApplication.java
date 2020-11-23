package com.soundlab.dockerizedjavaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Configuration
//@EnableSwagger2WebMvc
public class DockerizedJavaApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(DockerizedJavaApiApplication.class, args);
    }

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.soundlab.dockerizedjavaapi.controllers"))
//            .paths(PathSelectors.any())
//            .build()
//            .pathProvider(new PathProvider() {
//                @Override
//                public String getOperationPath(String operationPath) {
//                    return operationPath.replace("/api", "");
//                }
//
//                @Override
//                public String getResourceListingPath(String groupName, String apiDeclaration) {
//                    return "/api";
//                }
//            })
//            .apiInfo(apiInfo())
//            .directModelSubstitute(Timestamp.class, String.class);
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//            .title("VenturaHR API")
//            .description("Under construction")
//            .build();
//    }
}
