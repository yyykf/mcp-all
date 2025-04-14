package com.code4j.ai.mcp.server.feishu.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

/**
 * @Description
 * @Author YuKaiFan
 * @Date 2025/4/13 15:47
 */
@Data
public class BiTableAppVo implements Serializable {

    @Schema(description = "多维表格的token")
    private String appToken;
    @Schema(description = "多维表格名称")
    private String name;
    @Schema(description = "文章收集表ID")
    private String inboxTableId;
    @Schema(description = "关键事件表ID")
    private String keyTableId;
}
