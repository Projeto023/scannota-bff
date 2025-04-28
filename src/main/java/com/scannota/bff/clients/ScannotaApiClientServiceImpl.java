package com.scannota.bff.clients;

import com.scannota.bff.dto.mock.ProcessContentRequestDTO;
import com.scannota.bff.dto.mock.ProcessContentResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScannotaApiClientServiceImpl implements ScannotaApiClientService {
    final ScannotaApiFeignClient scannotaApiFeignClient;

    @Override
    public ProcessContentResponseDTO processContent(String content) {
        try {
            final ProcessContentRequestDTO requestDTO = ProcessContentRequestDTO
                    .builder().extractedText(content).build();
            return scannotaApiFeignClient.processContent(requestDTO);
        } catch (Exception e) {
            log.error("[ScannotaApiClientService] Error processing content: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
