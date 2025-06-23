package com.jimu.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lawrence
 * @Date 2025/6/23
 */

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatModel(ChatClient.Builder builder) {

        return builder.build();
    }
}
