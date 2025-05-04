package com.scannota.bff.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileUploadRequestDTO {
    @NotBlank(message = "Base64 file content cannot be blank")
    private String base64FileContent;
}
