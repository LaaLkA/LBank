package com.laalka.webservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://api-gateway:8089")
                .build();
    }
}

