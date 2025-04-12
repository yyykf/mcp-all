package com.code4j.ai.mcp.server.config;

import com.lark.oapi.Client;
import java.util.concurrent.TimeUnit;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * @Description
 * @Author YuKaiFan
 * @Date 2025/4/12 21:48
 */
@Slf4j
@Setter
@Configuration
@ConfigurationProperties(prefix = "remote.feishu")
public class FeiShuConfig {

    private String appId;
    private String appSecret;
    /** default request timeout */
    private Integer requestTimeout = 5000;

    /**
     * @link <a href="https://open.feishu.cn/document/server-side-sdk/java-sdk-guide/invoke-server-api">调用服务端 API</a>
     */
    @Bean
    public Client feishuClient() {
        log.info("[feishuClient] init feishu client, appId: {}, requestTimeout: {}ms", this.appId, this.requestTimeout);

        return Client.newBuilder(this.appId, this.appSecret)
                .requestTimeout(this.requestTimeout, TimeUnit.MILLISECONDS)
                .logReqAtDebug(true)
                .build();
    }

}
