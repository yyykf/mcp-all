package com.code4j.ai.mcp.client.controller.chat;

import static java.lang.StringTemplate.STR;

import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *@Description
 *@Author yukf02
 *@Date 2025/4/2 22:10
 */
@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatController {

    private ChatClient chatClient;
    private final @NonNull ChatClient.Builder chatClientBuilder;
    private final @NonNull ToolCallbackProvider tools;

    @PostConstruct
    void initChatClient() {
        log.info("""
                [initChatClient] available tools:
                {}
                """, Arrays.stream(tools.getToolCallbacks()).map(tool -> STR."""
                        {
                            "name": "\{tool.getName()}",
                            "description: "\{tool.getDescription()}"
                        }
                        """).collect(Collectors.joining()));
        this.chatClient = chatClientBuilder
                .defaultTools(tools)
                .build();
    }

    @GetMapping("/completions")
    public String completions(@RequestParam String message) {
        return chatClient.prompt(message).stream().content().collectList().block().stream().collect(Collectors.joining());
    }
}
