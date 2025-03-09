package com.example.desafiobackenditarc.clients;

import com.example.desafiobackenditarc.dto.cptec.WaveForecastDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@FeignClient(name = MockClientConfig.NAME, url = MockClientConfig.URL)
public interface MockFeignClient {
    @PostMapping("/mock")
    void notifyUser();
}
