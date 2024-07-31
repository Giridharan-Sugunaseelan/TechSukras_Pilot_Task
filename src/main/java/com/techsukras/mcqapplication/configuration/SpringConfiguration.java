package com.techsukras.mcqapplication.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringConfiguration {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public Docket mcqApi(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("MCQ-API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.techsukras.mcqapplication.controller"))
                .build();
    }
}
