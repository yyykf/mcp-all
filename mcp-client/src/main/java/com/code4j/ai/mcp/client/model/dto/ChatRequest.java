package com.code4j.ai.mcp.client.model.dto;

import cn.hutool.core.text.CharSequenceUtil;
import com.code4j.ai.mcp.common.exception.BusinessException;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/4/14 23:28
 */
public record ChatRequest(
        String message,
        String user
) {

    public ChatRequest {
        if (CharSequenceUtil.isBlank(message)) {
            throw new BusinessException("消息内容不能为空");
        }
    }
}
