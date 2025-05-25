package com.code4j.ai.mcp.server.feishu.assembler;

import com.code4j.ai.mcp.common.assmbler.DefaultMapstructConfig;
import com.code4j.ai.mcp.server.common.config.props.FeishuProperties.BiTable;
import com.code4j.ai.mcp.server.feishu.model.vo.BiTableAppVo;
import com.lark.oapi.service.bitable.v1.model.DisplayApp;
import org.mapstruct.*;

/**
 * @Description
 * @Author YuKaiFan
 * @Date 2025/4/13 15:53
 */
@Mapper(config = DefaultMapstructConfig.class)
public interface BiTableAssembler {

    @Mapping(target = "appToken", source = "source.appToken")
    BiTableAppVo toBiTableAppVo(DisplayApp source, BiTable table);

}
