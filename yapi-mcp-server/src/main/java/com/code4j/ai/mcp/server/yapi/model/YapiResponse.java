package com.code4j.ai.mcp.server.yapi.model;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/5/22 23:28
 */
@JsonClassDescription("Yapi通用响应结构")
public record YapiResponse<T>(
        Integer errcode,
        String errmsg,
        @JsonPropertyDescription("响应数据")
        T data
) {

    @JsonClassDescription("接口分类数据")
    public record CatMenuData(
            @JsonProperty("_id")
            Long id,
            @JsonPropertyDescription("接口分类名称")
            String name,
            @JsonPropertyDescription("Yapi项目ID")
            @JsonProperty("project_id")
            Long projectId,
            @JsonPropertyDescription("接口分类描述")
            String desc
    ) {

    }

    @JsonClassDescription("项目数据")
    public record ProjectData(
            @JsonProperty("_id")
            Long id,
            @JsonPropertyDescription("项目名称")
            String name,
            @JsonProperty("project_type")
            String projectType,
            Long uid,
            @JsonProperty("group_id")
            Long groupId,
            @JsonProperty("add_time")
            Long addTime,
            @JsonProperty("up_time")
            Long upTime
    ) {

    }

    @JsonClassDescription("分类下接口列表数据")
    public record CatInterfaceList(
            Integer count,
            Integer total,
            List<CatInterfaceData> list
    ) {

    }

    @JsonClassDescription("分类下接口数据")
    public record CatInterfaceData(
            @JsonProperty("_id")
            Long id,
            String title
    ) {

    }
}
