package com.example.desafiobackenditarc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationStatusEnum {
    PENDING("PENDING"),
    PROCESSED("PROCESSED"),
    ERROR("ERROR");

    private final String description;
}
