package com.wuzx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wuzhixuan
 * @date 2022/10/16 00:22
 * @Version 1.0
 */

@ConfigurationProperties(prefix = "aliyun.oss")
@Configuration
@Data
public class OSSConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketname;
}
