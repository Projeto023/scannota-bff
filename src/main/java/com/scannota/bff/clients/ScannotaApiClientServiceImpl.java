package com.scannota.bff.clients;

import com.scannota.bff.dto.mock.ProcessContentRequestDTO;
import com.scannota.bff.dto.mock.ProcessContentResponseDTO;
import com.scannota.bff.dto.request.AnalyzeInvoiceRequestDTO;
import com.scannota.bff.dto.request.FileUploadRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScannotaApiClientServiceImpl implements ScannotaApiClientService {
    final ScannotaApiFeignClient scannotaApiFeignClient;

    @Override
    public ProcessContentResponseDTO processContent(AnalyzeInvoiceRequestDTO content) {
        try {
//            final ProcessContentRequestDTO requestDTO = ProcessContentRequestDTO
//                    .builder().extractedText(content).build();
            return scannotaApiFeignClient.processContent(content);
        } catch (Exception e) {
            log.error("[ScannotaApiClientService] Error processing content: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void uploadFile(@RequestBody FileUploadRequestDTO request) {
        try {
            scannotaApiFeignClient.uploadFile(request);
        } catch (Exception e) {
            log.error("[ScannotaApiClientService] Error processing content: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
