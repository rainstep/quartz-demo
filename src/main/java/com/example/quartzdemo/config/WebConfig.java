package com.example.quartzdemo.config;

import com.example.quartzdemo.web.StringToDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public StringToDateConverter converter() {
        return new StringToDateConverter();
    }
}
