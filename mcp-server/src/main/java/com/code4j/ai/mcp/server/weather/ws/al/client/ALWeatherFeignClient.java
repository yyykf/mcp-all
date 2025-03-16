package com.code4j.ai.mcp.server.weather.ws.al.client;

import com.code4j.ai.mcp.server.weather.ws.al.model.req.WeatherRequest;
import com.code4j.ai.mcp.server.weather.ws.al.model.resp.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 天气预报Feign客户端接口
 * @Author AI Generated
 * @Date 2025/3/16
 */
@FeignClient(name = "al-weather-api", url = "${ws.al-api.host}")
public interface ALWeatherFeignClient {

    /**
     * 查询实况天气
     *
     * @param request 天气查询请求参数
     * @return 天气信息响应
     */
    @PostMapping("/api/tianqi")
    ALResponse<WeatherData> getWeather(@RequestBody WeatherRequest request);
}