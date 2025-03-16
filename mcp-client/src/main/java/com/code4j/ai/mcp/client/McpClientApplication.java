package com.code4j.ai.mcp.client;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
@EnableEncryptableProperties
public class McpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpClientApplication.class, args);
    }

    @Value("${ai.user.input}")
    private String userInput;

    @Bean
    public CommandLineRunner predefinedQuestions(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools,
            ConfigurableApplicationContext context) {

        return args -> {
            var chatClient = chatClientBuilder
                    .defaultTools(tools)
                    .build();

            log.info(">>> USER INPUT: {}", userInput);

            // FIXME: 2025/3/16 stream not work
            Flux<String> flux = chatClient.prompt(userInput).stream().content();
            log.info(">>> ASSISTANT: {}", flux.collectList().block().stream().collect(Collectors.joining()));

            context.close();
        };
    }
}
