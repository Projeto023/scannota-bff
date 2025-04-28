package com.scannota.bff.clients;

import com.scannota.bff.dto.mock.ProcessContentResponseDTO;
import com.scannota.bff.dto.request.AnalyzeInvoiceRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = ScannotaApiClientConfig.NAME,
        url = ScannotaApiClientConfig.URL,
        configuration = {ScannotaApiClientConfig.class})
public interface ScannotaApiFeignClient {
    @PostMapping("/api/v1/invoice/analyze")
    ProcessContentResponseDTO processContent(@RequestBody AnalyzeInvoiceRequestDTO requestDTO);
}
