package com.code4j.ai.mcp.server.yapi.service;

import com.code4j.ai.mcp.common.exception.BusinessException;
import com.code4j.ai.mcp.server.yapi.client.YapiClient;
import com.code4j.ai.mcp.server.yapi.config.YapiProperties;
import com.code4j.ai.mcp.server.yapi.model.YapiResponse;
import com.code4j.ai.mcp.server.yapi.model.YapiResponse.CatInterfaceData;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.*;
import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/5/22 23:30
 */
@Slf4j
@Service
public class YapiService {

    private final YapiClient yapiClient;
    private final YapiProperties yapiProperties;
    private final ExecutorService executorService = Executors.newFixedThreadPool(5); // 线程池，可根据需要调整

    public YapiService(YapiClient yapiClient, YapiProperties yapiProperties) {
        this.yapiClient = yapiClient;
        this.yapiProperties = yapiProperties;
    }

    @Tool(name = "listCategories", description = "根据Yapi项目ID查询接口分类列表")
    public List<CategoryInfo> listCategories(@ToolParam(description = "Yapi项目ID") Long projectId) {
        String token = yapiProperties.getProjectTokens().get(projectId);
        if (token == null) {
            throw new BusinessException("未找到项目ID为 " + projectId + " 的token配置");
        }
        YapiResponse<List<YapiResponse.CatMenuData>> response = yapiClient.getCatMenu(projectId, token);
        if (response == null || response.errcode() != 0 || response.data() == null) {
            throw new BusinessException("获取接口分类列表失败: " + (response != null ? response.errmsg() : "未知错误"));
        }

        List<CompletableFuture<CategoryInfo>> futures = response.data().stream()
                .map(catMenuData -> CompletableFuture.supplyAsync(() -> {
                    List<YapiResponse.CatInterfaceData> interfaces = null;
                    try {
                        // 默认获取所有接口，limit设置为足够大的数字
                        YapiResponse<YapiResponse.CatInterfaceList> interfaceListResponse = yapiClient.listCatInterfaces(
                                token, catMenuData.id(), 1, 1000);
                        if (interfaceListResponse != null && interfaceListResponse.errcode() == 0
                                && interfaceListResponse.data() != null) {
                            interfaces = interfaceListResponse.data().list();
                        }
                    } catch (Exception e) {
                        // 即使获取接口列表失败，也不影响分类的正常返回
                        log.error("[listCategories] get cat interfaces error.", e);
                    }
                    return new CategoryInfo(catMenuData.id(), catMenuData.name(), catMenuData.projectId(),
                            catMenuData.desc(), interfaces);
                }, executorService))
                .toList();

        return futures.stream()
                // 等待所有异步任务完成
                .map(CompletableFuture::join)
                .toList();
    }

    @Tool(name = "listProjects", description = "查看当前已经配置的Yapi项目列表，返回项目ID和项目名称")
    public List<ProjectInfo> listProjects() {
        Map<Long, String> projectTokens = yapiProperties.getProjectTokens();
        if (projectTokens == null || projectTokens.isEmpty()) {
            throw new BusinessException("未配置任何Yapi项目");
        }

        return projectTokens.entrySet().stream()
                .map(entry -> {
                    Long projectId = entry.getKey();
                    String token = entry.getValue();
                    YapiResponse<YapiResponse.ProjectData> response = yapiClient.getProject(token);
                    if (response != null && response.errcode() == 0 && response.data() != null) {
                        return new ProjectInfo(projectId, response.data().name());
                    } else {
                        throw new BusinessException(
                                "获取项目信息失败: " + (response != null ? response.errmsg() : "未知错误"));
                    }
                })
                .toList();
    }

    @Tool(name = "listCatInterfaces", description = "根据Yapi分类ID和项目ID查询分类下的接口列表")
    public List<YapiResponse.CatInterfaceData> listCatInterfaces(
            @ToolParam(description = "Yapi分类ID") Long catId,
            @ToolParam(description = "Yapi项目ID") Long projectId,
            @ToolParam(description = "当前页面，默认为1") Integer page,
            @ToolParam(description = "每页数量，默认为10，如果不想要分页数据，可将 limit 设置为比较大的数字，比如 1000") Integer limit) {

        String token = yapiProperties.getProjectTokens().get(projectId);
        if (token == null) {
            throw new BusinessException("未找到项目ID为 " + projectId + " 的token配置");
        }

        YapiResponse<YapiResponse.CatInterfaceList> response = yapiClient.listCatInterfaces(token, catId, page, limit);
        if (response != null && response.errcode() == 0 && response.data() != null) {
            return response.data().list();
        } else {
            throw new BusinessException(
                    "获取分类下接口列表失败: " + (response != null ? response.errmsg() : "未知错误"));
        }
    }

    public record ProjectInfo(
            @JsonPropertyDescription("项目ID")
            Long projectId,
            @JsonPropertyDescription("项目名称")
            String projectName
    ) {

    }

    public record CategoryInfo(
            @JsonPropertyDescription("接口分类ID")
            Long id,
            @JsonPropertyDescription("接口分类名称")
            String name,
            @JsonPropertyDescription("Yapi项目ID")
            Long projectId,
            @JsonPropertyDescription("接口分类描述")
            String desc,
            @JsonPropertyDescription("分类下的接口列表")
            List<CatInterfaceData> interfaces
    ) {

    }
}
