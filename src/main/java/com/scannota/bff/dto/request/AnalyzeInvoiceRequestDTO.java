package com.scannota.bff.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeInvoiceRequestDTO {
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private String content;
}
