package com.scannota.bff.clients;


import com.scannota.bff.dto.mock.ProcessContentResponseDTO;
import com.scannota.bff.dto.request.AnalyzeInvoiceRequestDTO;
import com.scannota.bff.dto.request.FileUploadRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface ScannotaApiClientService {
    ProcessContentResponseDTO processContent(AnalyzeInvoiceRequestDTO content);
    void uploadFile(@RequestBody FileUploadRequestDTO request);
}
