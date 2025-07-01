package com.jimu.tools;

import org.springframework.ai.tool.annotation.Tool;

/**
 * @Author lawrence
 * @Date 2025/7/1
 */
public class WeatherTool {



    @Tool(description = "查询某个城市的天气")
    public String weather(String city) {
        return "the weather in" + city + "is cloud";
    }
}
