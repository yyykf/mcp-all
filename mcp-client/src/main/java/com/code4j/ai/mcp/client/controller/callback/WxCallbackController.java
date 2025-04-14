package com.code4j.ai.mcp.client.controller.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/4/14 23:56
 */
@Slf4j
@RestController
@RequestMapping("/api/wx/callback")
public class WxCallbackController {

    @PostMapping(value = "/receive")
    public String receiveMsg(@RequestBody String jsonString) {
        log.info("[receiveMsg] jsonString: {}", jsonString);

        return "OK";
    }

}
