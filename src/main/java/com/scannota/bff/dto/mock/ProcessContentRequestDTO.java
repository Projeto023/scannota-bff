package com.scannota.bff.dto.mock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProcessContentRequestDTO {
    @JsonProperty("extracted_text")
    private String extractedText;
}
