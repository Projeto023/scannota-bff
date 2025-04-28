package com.scannota.bff.clients;


import com.scannota.bff.dto.mock.ProcessContentResponseDTO;
import com.scannota.bff.dto.request.AnalyzeInvoiceRequestDTO;

public interface ScannotaApiClientService {
    ProcessContentResponseDTO processContent(AnalyzeInvoiceRequestDTO content);
}
