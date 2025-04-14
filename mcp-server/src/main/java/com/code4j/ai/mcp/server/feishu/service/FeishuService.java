package com.code4j.ai.mcp.server.feishu.service;

import static com.code4j.ai.mcp.server.common.constants.AssemblerConstant.BI_TABLE_ASSEMBLER;

import com.code4j.ai.mcp.common.exception.BusinessException;
import com.code4j.ai.mcp.server.common.config.props.FeishuProperties;
import com.code4j.ai.mcp.server.feishu.model.vo.BiTableAppVo;
import com.lark.oapi.Client;
import com.lark.oapi.core.response.BaseResponse;
import com.lark.oapi.service.bitable.v1.model.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 飞书服务
 * @Author AI Generated
 * @Date 2025/3/16
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeishuService {

    private final @NonNull Client feishuClient;
    private final @NonNull FeishuProperties feishuProperties;

    /**
     * 获取多维表格元数据
     *
     * @return 多维表格元数据
     */
    @Tool(description = "获取多维表格元数据")
    public BiTableAppVo getBiTableApp() {
        try {
            GetAppReq param = GetAppReq.newBuilder().appToken(this.feishuProperties.getBiTable().appToken()).build();
            AtomicReference<BiTableAppVo> resultRef = new AtomicReference<>();

            GetAppResp value = this.feishuClient.bitable().v1().app().get(param);
            Optional.ofNullable(value)
                    .map(BaseResponse::getData)
                    .map(GetAppRespBody::getApp)
                    .map(app -> BI_TABLE_ASSEMBLER.toBiTableAppVo(app, this.feishuProperties.getBiTable()))
                    .ifPresent(resultRef::set);

            return resultRef.get();
        } catch (Exception e) {
            log.error("[getBitableApp] get bi app error.", e);
            throw new BusinessException(STR. "获取多维表格元数据失败, \{ e.getMessage() }" );
        }
    }

    @Tool(description = "获取表格字段定义")
    public List<AppTableFieldForList> getTableFields(@ToolParam(description = "多维表格的token") String appToken, @ToolParam(description="表格ID") String tableId) {
        try {
            ListAppTableFieldReq req = ListAppTableFieldReq.newBuilder()
                    .appToken(appToken)
                    .tableId(tableId)
                    .build();
            ListAppTableFieldResp resp = this.feishuClient.bitable().v1().appTableField().list(req);

            return Optional.ofNullable(resp)
                    .map(BaseResponse::getData)
                    .map(ListAppTableFieldRespBody::getItems)
                    .map(List::of)
                    .orElse(List.of());
        } catch (Exception e) {
            log.error("[getTableFields] get table fields error.", e);
            throw new BusinessException(STR. "获取表格字段定义失败, \{ e.getMessage() }" );
        }
    }
} 