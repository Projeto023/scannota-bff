package com.scannota.bff.service;


import com.scannota.bff.dto.request.AnalyzeInvoiceRequestDTO;
import com.scannota.bff.dto.response.AnalyseInvoiceResponseDTO;

public interface AnalyzeInvoiceService {
    AnalyseInvoiceResponseDTO process(AnalyzeInvoiceRequestDTO request);
}
