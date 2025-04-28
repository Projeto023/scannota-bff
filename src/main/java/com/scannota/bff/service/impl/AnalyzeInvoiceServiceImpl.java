package com.scannota.bff.service.impl;

import com.scannota.bff.clients.ScannotaApiClientService;
import com.scannota.bff.dto.mock.ProcessContentResponseDTO;
import com.scannota.bff.dto.request.AnalyzeInvoiceRequestDTO;
import com.scannota.bff.dto.response.AnalyseInvoiceResponseDTO;
import com.scannota.bff.service.AnalyzeInvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalyzeInvoiceServiceImpl implements AnalyzeInvoiceService {
    private final ScannotaApiClientService scannotaApiClientService;

    @Override
    public AnalyseInvoiceResponseDTO process(AnalyzeInvoiceRequestDTO request) {
        final ProcessContentResponseDTO response = scannotaApiClientService.processContent(request);
        return AnalyseInvoiceResponseDTO.builder().content(response.getRecords()).build();
    }
}
