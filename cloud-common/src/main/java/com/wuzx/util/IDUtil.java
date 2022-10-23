package com.wuzx.util;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

/**
 * ID生成器
 *
 * @author: wuzhixuan
 * @date 2022/10/24 01:11
 * @Version 1.0
 */
public class IDUtil {

    private static SnowflakeShardingKeyGenerator shardingKeyGenerator = new SnowflakeShardingKeyGenerator();

    /**
     * 雪花算法生成器,配置workId，避免􏰁复 *
     * 10进制 654334919987691526
     * 64位
     0000100100010100101010100010010010010110000000000000000000000110
     *
     * {@link SnowFlakeWordIdConfig}
     *
     * @return
     */
    public static Comparable<?> geneSnowFlakeID(){
        return shardingKeyGenerator.generateKey();
    }
}
