package com.example.desafiobackenditarc.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class NotifyForecastResponseUserErrorDTO {
    private Integer userId;
    private String errorMsg;
}
