package com.scannota.bff.clients;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ScannotaApiClientConfig {
    public static final String NAME = "mock";
    public static final String URL = "${client.scannota-api.url}";

    @Value("${client.scannota-api.token}")
    private String scannotaApiToken;

    @Bean
    public RequestInterceptor requestInterceptor() {
        log.info(scannotaApiToken);
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("Authorization", "Bearer " + scannotaApiToken);
        };
    }
}
