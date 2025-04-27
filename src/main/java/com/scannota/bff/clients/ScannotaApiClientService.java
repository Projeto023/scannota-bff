package com.scannota.bff.clients;


import com.scannota.bff.dto.mock.ProcessContentResponseDTO;

public interface ScannotaApiClientService {
    ProcessContentResponseDTO processContent(String content);
}
