package com.wuzx.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 分布式ID生成器Snowflake自定义wrokId
 *
 * @author: wuzhixuan
 * @date 2022/10/24 00:59
 * @Version 1.0
 */
@Configuration
@Slf4j
public class SnowFlakeWordIdConfig {


    /**
     *
     * 动态指定sharding jdbc 的雪花算法中的属性work.id属性
     * 通过调用System.setProperty()的方式实现,可用容器的 id 或者机器标识位
     * workId最大值 1L << 100，就是1024，即 0<= workId < 1024
     * {@link SnowflakeShardingKeyGenerator#getWorkerId()}
     */
    static {
        try {
            final InetAddress inetAddress = Inet4Address.getLocalHost();
            final String hostAddressIp = inetAddress.getHostAddress();

            final String workId = Math.abs(hostAddressIp.hashCode()) % 1024 + "";

            System.setProperty("workId", workId);
            log.info("workId {}", workId);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
