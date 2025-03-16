package com.code4j.ai.mcp.server.config;

import com.code4j.ai.mcp.server.weather.service.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.*;

/**
 * @Description
 * @Author YuKaiFan
 * @Date 2025/3/16 3:12
 */
@Configuration
public class ToolConfig {

    @Bean
    public ToolCallbackProvider weatherToolCallbackProvider(WeatherService weatherService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }
}
