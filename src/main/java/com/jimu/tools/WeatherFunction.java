package com.jimu.tools;

import java.util.function.Function;

/**
 * @Author lawrence
 * @Date 2025/7/1
 */
public class WeatherFunction implements Function<WeatherFunction.WeatherCity, String> {

    @Override
    public String apply(WeatherFunction.WeatherCity o) {
        return "the weather in " + o.city + "is cloud";
    }

    public static class WeatherCity {
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        private String city;

    }
}
