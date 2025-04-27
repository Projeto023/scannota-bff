package com.scannota.bff.clients;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ScannotaApiClientConfig {
    public static final String NAME = "mock";
    public static final String URL = "${client.scannota-api.url}";
}
