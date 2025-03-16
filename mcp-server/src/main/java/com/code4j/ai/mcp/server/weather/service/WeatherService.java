package com.code4j.ai.mcp.server.weather.service;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;
import com.code4j.ai.mcp.server.weather.ws.al.ALApiService;
import com.code4j.ai.mcp.server.weather.ws.al.model.resp.*;
import lombok.*;
import org.springframework.ai.tool.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author YuKaiFan
 * @Date 2025/3/15 18:54
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherService {

    private final @NonNull ALApiService alApiService;

    /**
     * 根据经纬度获取天气预报
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return 对应位置的天气预报
     */
    @Tool(description = """
            获取天气预报，支持多维度查询：
            1. 经纬度查询（优先级最高），比如 {"latitude": "22.5", "longitude": "114"}
            2. 省份+城市查询（优先级次之）（，比如 {"province": "广东", "city": "深圳"}
            3. ip查询（优先级最低），比如 {"ip": "127.0.0.1"}
            """)
    public String getWeatherForecast(@ToolParam(description = "维度", required = false) String latitude,
            @ToolParam(description = "经度", required = false) String longitude,
            @ToolParam(description = "省份", required = false) String province,
            @ToolParam(description = "城市", required = false) String city,
            @ToolParam(description = "ip", required = false) String ip) {

        ALResponse<WeatherData> response;
        if (CharSequenceUtil.isAllNotBlank(latitude,longitude)) {
            response = this.alApiService.getWeatherByLocation(latitude, longitude);
        } else if (CharSequenceUtil.isAllNotBlank(province, city)) {
            response = this.alApiService.getWeather(province, city);
        } else if (CharSequenceUtil.isNotBlank(ip)) {
            response = this.alApiService.getWeatherByIp(ip);
        } else {
            return "参数错误";
        }

        return JSONUtil.toJsonStr(response);
    }

}
