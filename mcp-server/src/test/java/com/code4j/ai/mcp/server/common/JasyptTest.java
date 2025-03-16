package com.code4j.ai.mcp.server.common;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class JasyptTest {

    @Resource
    private StringEncryptor stringEncryptor;

    @Test
    void encryptPwd() {
        log.info("{}", stringEncryptor.encrypt(""));
    }
}