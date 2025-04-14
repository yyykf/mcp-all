package com.code4j.ai.mcp.server.common.config.props;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author YuKaiFan
 * @Date 2025/4/13 15:38
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "remote.feishu")
public class FeishuProperties {

    private String appId;
    private String appSecret;
    /** default request timeout */
    private Integer requestTimeout = 5000;
    private BiTable biTable;

    public record BiTable(String appToken, String inboxTableId, String keyTableId) {

    }

}
