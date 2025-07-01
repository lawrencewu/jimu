package com.jimu.config;

import com.jimu.tools.WeatherService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.VectorStoreChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

/**
 * @Author lawrence
 * @Date 2025/6/23
 */

@Configuration
public class ChatClientConfig {

    @Autowired
    JdbcChatMemoryRepository jdbcChatMemoryRepository;

    @Autowired
    public VectorStore vectorStore;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        // 构建对话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(10)
                .build();

        // 在mysql中存储对话
        Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();

        // 从向量库中查询数据
        Advisor questionAnswerAdvisor = QuestionAnswerAdvisor.builder(vectorStore)
                .build();


        return builder.defaultAdvisors(
                        messageChatMemoryAdvisor, questionAnswerAdvisor)
                .build();
    }

    @Bean
    @Description("某个城市今天的天气")
    public WeatherService weatherService() {
        return new WeatherService();
    }
}
