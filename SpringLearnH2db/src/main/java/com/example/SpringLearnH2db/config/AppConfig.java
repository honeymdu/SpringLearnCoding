package com.example.SpringLearnH2db.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.SpringLearnH2db.Auth.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
public class AppConfig {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @SuppressWarnings("rawtypes")
    @Bean
    AuditorAware getAuditorAwareImpl(){
        return new AuditorAwareImpl();
    }

   
}
