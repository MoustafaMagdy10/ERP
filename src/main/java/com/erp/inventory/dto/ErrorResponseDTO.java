package com.erp.inventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.Map;



@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ErrorResponseDTO {
    private final String code;
    private final String message;
    private final Instant timestamp;
    private final Map<String, Object> details;
}
