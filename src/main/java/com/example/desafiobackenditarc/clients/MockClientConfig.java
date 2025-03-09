package com.example.desafiobackenditarc.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MockClientConfig {
    public static final String NAME = "mock";
    public static final String URL = "${client.mock.url}";
}
