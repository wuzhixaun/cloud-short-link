package com.wuzx.component;

import com.wuzx.strategy.ShadingDBConfig;
import com.wuzx.strategy.ShadingTabeConfig;
import com.wuzx.util.CommonUtil;
import org.springframework.stereotype.Component;

/**
 * 短链生成组件
 *
 * @author: wuzhixuan
 * @date 2022/10/30 11:45
 * @Version 1.0
 */
@Component
public class ShortLinkComponent {
    /**
     * 62个字符
     */
    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 创建短链
     *
     * @param originalUrl
     * @return db编码+6位短链编码
     */
    public String createShortLinkCode(String originalUrl) {
        // murmurhash生成32hash编码
        final long murmurHash32 = CommonUtil.murmurHash32(originalUrl);

        //转62进制
        String shortLinkCode = encodeToBase62(murmurHash32);

        return ShadingDBConfig.getRandomDbPrefix() + shortLinkCode + ShadingTabeConfig.getRandomTablePrefix();
    }

    /**
     * 十进制转62进制
     *
     * @param murmurHash32
     * @return
     */
    private String encodeToBase62(long murmurHash32) {
        // StringBUffer线程安全
        StringBuilder sb = new StringBuilder();

        do {
            int i = (int) (murmurHash32 % 62);
            sb.append(CHARS.charAt(i));
            murmurHash32 = murmurHash32 / 62;
        } while (murmurHash32 > 0);

        String value = sb.reverse().toString();
        return value;

    }


}
