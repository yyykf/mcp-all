package com.code4j.ai.mcp.client.service;

import com.code4j.ai.mcp.client.model.dto.*;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/4/14 23:41
 */
public interface ChatService {

    /**
     * 处理对话请求
     *
     * @param request 对话请求
     * @return 对话响应
     */
    ChatResponse chat(ChatRequest request);
}