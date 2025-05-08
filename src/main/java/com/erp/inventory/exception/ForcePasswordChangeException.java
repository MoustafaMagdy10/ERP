package com.erp.inventory.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

 // Thrown when a user must change their password before proceeding
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForcePasswordChangeException extends RuntimeException {

    public ForcePasswordChangeException(String message) {
        super(message);
    }

    public ForcePasswordChangeException(String message, Throwable cause) {
        super(message, cause);
    }
}