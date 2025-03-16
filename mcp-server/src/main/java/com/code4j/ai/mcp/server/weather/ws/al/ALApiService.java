package com.code4j.ai.mcp.server.weather.ws.al;

import com.code4j.ai.mcp.server.weather.ws.al.client.ALWeatherFeignClient;
import com.code4j.ai.mcp.server.weather.ws.al.model.req.WeatherRequest;
import com.code4j.ai.mcp.server.weather.ws.al.model.resp.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description 天气预报API服务
 * @Author YuKaiFan
 * @Date 2025/3/16 15:07
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ALApiService {

    private final @NonNull ALWeatherFeignClient alWeatherFeignClient;

    @Value("${ws.al-api.token}")
    private String token;

    /**
     * 查询实况天气
     *
     * @param province 省份
     * @param city     城市名称
     * @return 天气信息响应
     */
    public ALResponse<WeatherData> getWeather(String province, String city) {
        WeatherRequest request = WeatherRequest.builder()
                .token(this.token)
                .province(province)
                .city(city)
                .build();
        return alWeatherFeignClient.getWeather(request);
    }

    /**
     * 根据城市ID查询实况天气
     *
     * @param cityId 城市ID
     * @return 天气信息响应
     */
    public ALResponse<WeatherData> getWeatherByCityId(String cityId) {
        WeatherRequest request = WeatherRequest.builder()
                .token(this.token)
                .cityId(cityId)
                .build();
        return alWeatherFeignClient.getWeather(request);
    }

    /**
     * 根据IP查询实况天气
     *
     * @param ip IP地址
     * @return 天气信息响应
     */
    public ALResponse<WeatherData> getWeatherByIp(String ip) {
        WeatherRequest request = WeatherRequest.builder()
                .token(this.token)
                .ip(ip)
                .build();
        return alWeatherFeignClient.getWeather(request);
    }

    /**
     * 根据经纬度查询实况天气
     *
     * @param lon 经度
     * @param lat 纬度
     * @return 天气信息响应
     */
    public ALResponse<WeatherData> getWeatherByLocation(String lon, String lat) {
        WeatherRequest request = WeatherRequest.builder()
                .token(this.token)
                .lon(lon)
                .lat(lat)
                .build();
        return alWeatherFeignClient.getWeather(request);
    }
}
