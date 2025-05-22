package com.code4j.ai.mcp.server.yapi.config;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/5/22 23:29
 */
@Configuration
@ConfigurationProperties(prefix = "yapi")
@Data
public class YapiProperties {

    private String baseUrl;
    private Map<Long, String> projectTokens;
}
