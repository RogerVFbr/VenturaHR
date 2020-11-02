package com.soundlab.dockerizedjavaapi.configuration;

import com.soundlab.dockerizedjavaapi.mappers.CandidatoFromSignUpRequestUserMapper;
import com.soundlab.dockerizedjavaapi.mappers.EmpresaFromSignUpRequestUserMapper;
import com.soundlab.dockerizedjavaapi.mappers.RespostaFromResponderVagaViewRequestResposta;
import com.soundlab.dockerizedjavaapi.mappers.VagaFromPublishVagaViewRequestVaga;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
        modelMapper.addMappings(new VagaFromPublishVagaViewRequestVaga());
        modelMapper.addMappings(new RespostaFromResponderVagaViewRequestResposta());
//        modelMapper.addMappings(new ResponderVagaViewResponseContentFromVaga());
        return modelMapper;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**/**")
            .allowCredentials(false)
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*");
    }
}
