package com.example.desafiobackenditarc.exception;

public class DesafioBackendItArcApiException extends Exception {
    private final Object additionalInfo;

    public DesafioBackendItArcApiException(String message) {
        super(message);
        this.additionalInfo = null;
    }

    public DesafioBackendItArcApiException(String message, Object additionalInfo) {
        super(message);
        this.additionalInfo = additionalInfo;
    }

    public Object getAdditionalInfo() {
        return additionalInfo;
    }
}