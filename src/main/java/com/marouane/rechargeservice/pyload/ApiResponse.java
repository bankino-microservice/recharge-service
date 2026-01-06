package com.marouane.rechargeservice.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String code;
    private int status;         // HTTP status code (ex: 400)
    private String message;     // Human-readable message
    private T data;             // Any response data (null for errors)
    private Map<String, String> errors;  // Field errors for validation
}
