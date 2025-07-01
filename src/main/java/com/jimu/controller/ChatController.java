package com.jimu.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lawrence
 * @Date 2025/6/23
 */

@RestController
public class ChatController {

    @Autowired
    public ChatClient chatClient;

    // 用于测试
    @GetMapping("/chat")
    public String chat(String userText) {

        return chatClient
                .prompt()
                .user(userText)
                .call()
                .content();
    }
}
