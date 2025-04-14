package com.code4j.ai.mcp.client.controller.chat;

import com.code4j.ai.mcp.client.model.dto.chat.*;
import com.code4j.ai.mcp.client.service.ChatService;
import com.code4j.ai.mcp.common.model.ApiResponse;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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

    private final @NonNull ChatService chatService;

    @PostMapping("/completions")
    public ResponseEntity<ApiResponse<ChatResponse>> chat(@RequestBody ChatRequest request) {
        try {
            ChatResponse response = chatService.chat(request);
            return new ResponseEntity<>(new ApiResponse<>("SUCCESS", "对话成功", response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>("ERROR", e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
