package com.code4j.ai.mcp.server.config;

import feign.*;
import java.util.concurrent.TimeUnit;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.*;

/**
 * @Description Feign客户端配置
 * @Author AI Generated
 * @Date 2025/3/16
 */
@Configuration
@EnableFeignClients(basePackages = "com.code4j.ai.mcp.server")
public class FeignConfig {

    /**
     * 配置Feign日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 配置Feign请求超时时间
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(
                10, TimeUnit.SECONDS,
                60, TimeUnit.SECONDS,
                true);
    }

}