package com.code4j.ai.mcp.server.weather.ws.al;

import com.code4j.ai.mcp.server.weather.ws.al.model.resp.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ALApiServiceTest {

    private final @NonNull ALApiService alApiService;

    @Test
    void getWeather() {
        ALResponse<WeatherData> resp = alApiService.getWeather("广东", "深圳");
        log.info("{}", resp);
    }

}