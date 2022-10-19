package com.wuzx.config;

import com.wuzx.model.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author: wuzhixuan
 * @date 2022/10/20 01:07
 * @Version 1.0
 */
@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                // 添加拦截路径
                .addPathPatterns("/api/account/*/**", "/api/traffic/*/**")
                // 排除不拦截
                .excludePathPatterns("/api/account/*/register", "/api/account/*/upload", "/api/account/*/login",
                        "/api/account/*/captcha", "/api/account/*/send_code");
    }
}
