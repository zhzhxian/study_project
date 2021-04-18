package com.zzx.esdemo02;

import com.zzx.esdemo02.service.JdContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EsDemo02ApplicationTests {
    @Autowired
    JdContentService jdContentService;

    @Test
    void testParseContent() throws Exception {
        jdContentService.parseContent("java");
    }

}
