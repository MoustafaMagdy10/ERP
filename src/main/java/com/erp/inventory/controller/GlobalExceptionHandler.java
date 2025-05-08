package com.erp.inventory.controller;

import com.erp.inventory.dto.ErrorResponseDTO;
import com.erp.inventory.exception.ForcePasswordChangeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ForcePasswordChangeException.class)
    public ResponseEntity<ErrorResponseDTO> handleForcePasswordChange() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponseDTO.builder()
                        .code("PASSWORD_CHANGE_REQUIRED")
                        .message("You must change your password before proceeding")
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDTO.builder()
                        .code("INTERNAL_SERVER_ERROR")
                        .message("Check Code")
                        .build());
    }
}
