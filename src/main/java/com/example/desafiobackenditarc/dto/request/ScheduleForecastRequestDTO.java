package com.example.desafiobackenditarc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleForecastRequestDTO {
    @NotEmpty
    private String cityName;
    @NotEmpty
    private Date notificationDate;
}
