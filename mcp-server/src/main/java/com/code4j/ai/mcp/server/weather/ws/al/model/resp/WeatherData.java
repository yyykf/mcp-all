package com.code4j.ai.mcp.server.weather.ws.al.model.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * @Description 天气数据实体
 * @Author AI Generated
 * @Date 2025/3/16
 */
@Data
public class WeatherData {

    private String city;

    @JsonProperty("city_en")
    private String cityEn;

    private String province;

    @JsonProperty("province_en")
    private String provinceEn;

    @JsonProperty("city_id")
    private String cityId;

    private String date;

    @JsonProperty("update_time")
    private String updateTime;

    private String weather;

    @JsonProperty("weather_code")
    private String weatherCode;

    private Double temp;

    @JsonProperty("min_temp")
    private Integer minTemp;

    @JsonProperty("max_temp")
    private Integer maxTemp;

    private String wind;

    @JsonProperty("wind_speed")
    private String windSpeed;

    @JsonProperty("wind_power")
    private String windPower;

    private String rain;

    @JsonProperty("rain_24h")
    private String rain24h;

    private String humidity;
    private String visibility;
    private String pressure;

    @JsonProperty("tail_number")
    private String tailNumber;

    private String air;

    @JsonProperty("air_pm25")
    private String airPm25;

    private String sunrise;
    private String sunset;
    private AirQuality aqi;

    private List<WeatherIndex> index;
    private List<HourlyForecast> hour;
    private List<WeatherAlarm> alarm;

    @Data
    public static class AirQuality {

        private String air;

        @JsonProperty("air_level")
        private String airLevel;

        @JsonProperty("air_tips")
        private String airTips;

        private String pm25;
        private String pm10;
        private String co;
        private String no2;
        private String so2;
        private String o3;
    }

    @Data
    public static class WeatherIndex {

        private String type;
        private String level;
        private String name;
        private String content;
    }

    @Data
    public static class HourlyForecast {

        private String time;
        private String temp;
        private String wea;

        @JsonProperty("wea_code")
        private String weaCode;

        private String wind;

        @JsonProperty("wind_level")
        private String windLevel;
    }

    @Data
    public static class WeatherAlarm {

        private String type;
        private String level;
        private String content;
    }
} 