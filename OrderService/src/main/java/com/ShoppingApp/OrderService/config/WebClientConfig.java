package com.ShoppingApp.OrderService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean   //Create bean in spring container with name webClient
    public WebClient webClient() {
        return WebClient.builder().build();
    }

}
