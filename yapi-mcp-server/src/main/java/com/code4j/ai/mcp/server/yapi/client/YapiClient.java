package com.code4j.ai.mcp.server.yapi.client;

import com.code4j.ai.mcp.server.yapi.model.YapiResponse;
import com.code4j.ai.mcp.server.yapi.model.YapiResponse.CatMenuData;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/5/22 23:29
 */
@FeignClient(name = "yapiClient", url = "#{yapiProperties.baseUrl}")
public interface YapiClient {

    @GetMapping("/api/interface/getCatMenu")
    YapiResponse<List<CatMenuData>> getCatMenu(@RequestParam("project_id") Long projectId,
            @RequestParam("token") String token);

    @GetMapping("/api/project/get")
    YapiResponse<YapiResponse.ProjectData> getProject(@RequestParam("token") String token);

    @GetMapping("/api/interface/list_cat")
    YapiResponse<YapiResponse.CatInterfaceList> listCatInterfaces(@RequestParam("token") String token,
            @RequestParam("catid") Long catid,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit);
}
