package ru.parmacoworking.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseEntity {

    private String message;
    private String error;
    private int status;

    public ErrorResponseEntity(String message, String error, int status) {
        this.message = message;
        this.error = error;
        this.status = status;
    }
}
