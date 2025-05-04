package com.scannota.bff.service.impl;

import com.scannota.bff.clients.ScannotaApiClientService;
import com.scannota.bff.dto.request.FileUploadRequestDTO;
import com.scannota.bff.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final ScannotaApiClientService scannotaApiClientService;

    @Override
    public void uploadFile(FileUploadRequestDTO request) {
        log.info("Processing file upload request in BFF");
        scannotaApiClientService.uploadFile(request);
        log.info("Successfully processed file upload request in BFF");
    }
}
