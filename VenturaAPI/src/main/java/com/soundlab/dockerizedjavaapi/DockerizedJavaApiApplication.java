package com.soundlab.dockerizedjavaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@Configuration
@OpenAPIDefinition(info = @Info(
    title = "VenturaRH - Definição OpenApi",
    version = "1.0",
    description = "Endpoints expostos na API do sistema de gerenciamento de oferta de vagas de " +
        "trabalho VenturaHR.") )
public class DockerizedJavaApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(DockerizedJavaApiApplication.class, args);
    }

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    }
}
