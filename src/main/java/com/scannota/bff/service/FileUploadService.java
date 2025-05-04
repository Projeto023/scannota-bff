package com.scannota.bff.service;

import com.scannota.bff.dto.request.FileUploadRequestDTO;

public interface FileUploadService {
    void uploadFile(FileUploadRequestDTO request);
}
