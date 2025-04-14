package com.code4j.ai.mcp.server.common.constants;

import cn.hutool.extra.spring.SpringUtil;
import com.code4j.ai.mcp.server.feishu.assembler.BiTableAssembler;
import lombok.*;

/**
 * @Description
 * @Author KaiFan Yu
 * @Date 2025/4/13 16:58
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssemblerConstant {

    public static final BiTableAssembler BI_TABLE_ASSEMBLER = SpringUtil.getBean(BiTableAssembler.class);

}
