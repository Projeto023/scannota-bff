package com.scannota.bff.controller;

import com.scannota.bff.dto.request.FileUploadRequestDTO;
import com.scannota.bff.service.FileUploadService;
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
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    @Operation(description = "Uploads a file in base64 format")
    public ResponseEntity uploadFile(@Valid @RequestBody final FileUploadRequestDTO request) {
        fileUploadService.uploadFile(request);
        return buildSuccessResponse(null);
    }
}
