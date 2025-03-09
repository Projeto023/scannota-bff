package com.example.desafiobackenditarc.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NotifyForecastResponseDTO {
    private List<NotifyForecastResponseUserErrorDTO> usersWithNotificationError;
}
