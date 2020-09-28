package com.soundlab.dockerizedjavaapi.configuration;

import com.soundlab.dockerizedjavaapi.mappers.CandidatoFromSignUpRequestUserMapper;
import com.soundlab.dockerizedjavaapi.mappers.EmpresaFromSignUpRequestUserMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
class WebserviceConfiguration implements WebMvcConfigurer {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new EmpresaFromSignUpRequestUserMapper());
        modelMapper.addMappings(new CandidatoFromSignUpRequestUserMapper());

        return modelMapper;
    }
}
