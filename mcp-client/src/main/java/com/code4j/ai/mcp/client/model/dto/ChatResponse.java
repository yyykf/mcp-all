package com.code4j.ai.mcp.client.model.dto;

import cn.hutool.core.text.CharSequenceUtil;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/4/14 23:28
 */
public record ChatResponse(
        String response,
        String model
) {

    public ChatResponse {
        if (CharSequenceUtil.isBlank(response)) {
            throw new IllegalArgumentException("响应内容不能为空");
        }
    }
} 