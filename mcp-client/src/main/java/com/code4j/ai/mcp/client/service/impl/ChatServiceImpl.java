package com.code4j.ai.mcp.client.service.impl;

import com.code4j.ai.mcp.client.model.dto.chat.*;
import com.code4j.ai.mcp.client.service.ChatService;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/4/14 23:41
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;
    private final @NonNull ChatClient.Builder chatClientBuilder;
    private final @NonNull ToolCallbackProvider tools;

    @PostConstruct
    void initChatClient() {
        log.info("""
                [initChatClient] available tools:
                {}
                """, Arrays.stream(this.tools.getToolCallbacks()).map(tool -> STR. """
                        {
                            "name": "\{ tool.getToolDefinition().name() }",
                            "description: "\{ tool.getToolDefinition().description() }"
                        }
                        """ ).collect(Collectors.joining()));
        this.chatClient = this.chatClientBuilder
                .defaultToolCallbacks(this.tools)
                .build();
    }

    @Override
    public ChatResponse chat(ChatRequest request) {
        // 创建用户消息
        Message userMessage = new UserMessage(request.message());

        String response = this.chatClient.prompt(new Prompt(userMessage))
                .stream()
                .content()
                .collectList()
                .block()
                .stream()
                .collect(Collectors.joining());

        // 获取模型名称
        // String modelName = this.chatClient.getDefaultModelName();
        String modelName = "";

        return new ChatResponse(response, modelName);
    }
}
