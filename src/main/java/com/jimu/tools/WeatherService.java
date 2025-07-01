package com.jimu.tools;

import java.util.function.Function;

/**
 * @Author lawrence
 * @Date 2025/7/1
 */
public class WeatherService implements Function<WeatherService.WeatherCity, String> {

    @Override
    public String apply(WeatherService.WeatherCity o) {
        return "the weather in " + o.city + "is cloud";
    }

    static class WeatherCity {
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        private String city;

    }
}
