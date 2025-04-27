package com.scannota.bff.controller;


import com.scannota.bff.dto.request.AnalyzeInvoiceRequestDTO;
import com.scannota.bff.dto.response.AnalyseInvoiceResponseDTO;
import com.scannota.bff.service.AnalyzeInvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.scannota.bff.utils.ApiResponseUtil.buildSuccessResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice")
public class InvoiceController {
    private final AnalyzeInvoiceService analyzeInvoiceService;
    @PostMapping("/analyze")
    @Operation(description = "Analyzes invoice")
    public ResponseEntity analyzeInvoice(@Valid @RequestBody final AnalyzeInvoiceRequestDTO request) {
        final AnalyseInvoiceResponseDTO response = analyzeInvoiceService.process(request);
        return buildSuccessResponse(response);
    }
}
