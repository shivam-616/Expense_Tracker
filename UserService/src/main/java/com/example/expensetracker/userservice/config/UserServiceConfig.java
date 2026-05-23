package com.example.expensetracker.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class UserServiceConfig
{

    @Bean
    public ObjectMapper objectMapperInit(){
        return new ObjectMapper();
    }

}
