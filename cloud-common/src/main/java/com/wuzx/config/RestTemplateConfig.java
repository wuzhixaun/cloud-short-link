package com.wuzx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author: wuzhixuan
 * @date 2022/10/12 01:11
 * @Version 1.0
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory) {
        return new RestTemplate(requestFactory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        final SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000); // 读取超时时间
        factory.setConnectTimeout(10000); // 连接超时
        return factory;
    }
}
