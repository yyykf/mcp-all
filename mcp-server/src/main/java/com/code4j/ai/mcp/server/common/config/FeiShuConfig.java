package com.code4j.ai.mcp.server.common.config;

import com.code4j.ai.mcp.server.common.config.props.FeishuProperties;
import com.lark.oapi.Client;
import java.util.concurrent.TimeUnit;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;

/**
 * @Description
 * @Author YuKaiFan
 * @Date 2025/4/12 21:48
 */
@Slf4j
@Setter
@Configuration
public class FeiShuConfig {

    /**
     * @link <a href="https://open.feishu.cn/document/server-side-sdk/java-sdk-guide/invoke-server-api">调用服务端 API</a>
     */
    @Bean
    public Client feishuClient(FeishuProperties feishuProperties) {
        String appId = feishuProperties.getAppId();
        String appSecret = feishuProperties.getAppSecret();
        Integer requestTimeout = feishuProperties.getRequestTimeout();

        log.info("[feishuClient] init feishu client, appId: {}, requestTimeout: {}ms", appId, requestTimeout);

        return Client.newBuilder(appId, appSecret)
                .requestTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .logReqAtDebug(true)
                .build();
    }

}
