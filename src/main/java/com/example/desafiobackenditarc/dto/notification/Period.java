package com.example.desafiobackenditarc.dto.notification;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Period {
    private String day;
    private String agitation;
    private Double height;
    private String direction;
    private Double wind;
    private String windDirection;
}
