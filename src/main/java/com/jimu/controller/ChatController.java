package com.jimu.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
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

    @Autowired
    public ChatMemory chatMemory;

    // 用于测试
    @GetMapping("/test")
    public String chat(){



        return chatClient
                .prompt()
                .user("hello JIMU" )
                .call()
                .content();
    }
}
