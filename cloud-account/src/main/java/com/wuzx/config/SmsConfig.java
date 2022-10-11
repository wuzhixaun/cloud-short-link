package com.wuzx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * sms配置类
 */
@ConfigurationProperties(prefix = "sms")
@Configuration
@Data
public class SmsConfig {

    private String templateId;

    private String appCode;

}