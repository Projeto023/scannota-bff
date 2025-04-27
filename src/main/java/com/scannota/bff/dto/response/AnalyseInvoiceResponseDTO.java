package com.scannota.bff.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class AnalyseInvoiceResponseDTO {
    private Object content;
}
