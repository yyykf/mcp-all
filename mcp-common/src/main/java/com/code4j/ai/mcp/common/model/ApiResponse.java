package com.code4j.ai.mcp.common.model;

import lombok.*;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/4/14 23:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String result;
    private String message;
    private T data;
} 