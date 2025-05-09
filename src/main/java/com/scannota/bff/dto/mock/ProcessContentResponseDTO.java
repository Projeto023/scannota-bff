package com.scannota.bff.dto.mock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
@Getter
public class ProcessContentResponseDTO {
    private Boolean success;
    private String message;
    private Object records;
}
