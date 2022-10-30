package com.wuzx.biz;

import com.wuzx.LinkApplication;
import com.wuzx.component.ShortLinkComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @author: wuzhixuan
 * @date 2022/10/30 11:53
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LinkApplication.class)
@Slf4j
public class ShortLinkTest {

    @Autowired
    private ShortLinkComponent shortLinkComponent;

    /**
     * 测试生成短链码
     */
    @Test
    public void testCreateShortLink() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int num1 = random.nextInt(10);
            int num2 = random.nextInt(1000000);
            int num3 = random.nextInt(1000000);
            String originalUrl = num1 + "xdclass" +
                    num2 + ".net" + num3;
            String shortLinkCode = shortLinkComponent.createShortLinkCode(originalUrl);
            log.info("originalUrl:" + originalUrl +
                    ", shortLinkCode=" + shortLinkCode);
        }

    }
}
