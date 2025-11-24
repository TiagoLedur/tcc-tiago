package com.example.stock_control_api.exception;

public class MensagemErro {
    private final String message;

    public MensagemErro(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
