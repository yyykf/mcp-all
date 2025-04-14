package com.code4j.ai.mcp.client.common.handler;

import com.code4j.ai.mcp.common.model.ApiResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/4/14 23:40
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static ResponseEntity<ApiResponse<String>> errorResponseEntity(String message, HttpStatus status) {
        ApiResponse<String> response = new ApiResponse<>("ERROR", message, null);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return errorResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}