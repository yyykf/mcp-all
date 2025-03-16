package com.code4j.ai.mcp.server.weather.ws.al.model.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description
 * @Author YuKaiFan
 * @Date 2025/3/16 16:30
 */
@Data
public class ALResponse<T> {
    
    private String code;
    private String message;
    private Boolean success;
    private T data;
    private Long time;
    @JsonProperty("request_id")
    private String requestId;
    private Integer usage;
}
