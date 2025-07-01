package com.jimu.controller;

import com.jimu.tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lawrence
 * @Date 2025/7/1
 *
 * 从DeepSeek-V2开始支持tools功能
 *
 * funtion calling
 */
@RestController
public class WeatherController {

    @Autowired
    public ChatClient chatClient;

    @GetMapping("/weather")
    public String weather(@RequestParam("city") String city){
       return chatClient.prompt("今天的天气")
               .tools(new WeatherTool())
               .call()
               .content();
    }

    @GetMapping("/weather/service")
    public String weatherService(@RequestParam("city") String city){
        return chatClient.prompt("今天的天气")
                .tools("weatherService")
                .call()
                .content();
    }
}
