package com.code4j.ai.mcp.server.weather.ws.al.model.req;

import lombok.*;

/**
 * @Description 天气查询请求参数
 * @Author AI Generated
 * @Date 2025/3/16
 */
@Data
@Builder
public class WeatherRequest {

    /**
     * API调用token
     */
    private String token;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 经度
     */
    private String lon;

    /**
     * 纬度
     */
    private String lat;
} 