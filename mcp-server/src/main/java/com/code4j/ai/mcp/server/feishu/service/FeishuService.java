package com.code4j.ai.mcp.server.feishu.service;

import cn.hutool.json.JSONUtil;
import com.lark.oapi.Client;
import com.lark.oapi.core.response.BaseResponse;
import com.lark.oapi.service.bitable.v1.model.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.*;
import org.springframework.ai.tool.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 飞书服务
 * @Author AI Generated
 * @Date 2025/3/16
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeishuService {

    private final @NonNull Client feishuClient;

    /**
     * 获取多维表格元数据
     *
     * @param appToken 多维表格的应用token
     * @return 多维表格应用信息
     */
    @Tool(description = "获取多维表格元数据")
    public String getBitableApp(@ToolParam(description = "多维表格应用token") String appToken) {
        try {
            GetAppReq param = GetAppReq.newBuilder().appToken(appToken).build();
            AtomicReference<DisplayApp> resultRef = new AtomicReference<>();

            Optional.ofNullable(this.feishuClient.bitable().v1().app().get(param))
                    .map(BaseResponse::getData)
                    .map(GetAppRespBody::getApp)
                    .ifPresent(resultRef::set);

            return JSONUtil.toJsonStr(resultRef.get());
        } catch (Exception e) {
            return "获取多维表格应用信息失败: " + e.getMessage();
        }
    }
} 