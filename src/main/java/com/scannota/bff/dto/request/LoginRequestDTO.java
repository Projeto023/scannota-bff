package com.scannota.bff.dto.request;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String id;
    private String email;
    private String name;
    private String imageUrl;
}
