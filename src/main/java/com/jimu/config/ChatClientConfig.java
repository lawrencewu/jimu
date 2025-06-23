package com.jimu.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lawrence
 * @Date 2025/6/23
 */

@Configuration
public class ChatClientConfig {

    @Autowired
    JdbcChatMemoryRepository jdbcChatMemoryRepository;

    @Bean
    public ChatClient chatModel(ChatClient.Builder builder) {
        // 构建对话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(10)
                .build();
        return builder.defaultAdvisors(
                MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
}
