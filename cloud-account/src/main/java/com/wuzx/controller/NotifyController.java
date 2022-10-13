package com.wuzx.controller;

import com.google.code.kaptcha.Producer;
import com.wuzx.controller.request.SendCodeRequest;
import com.wuzx.enums.BizCodeEnum;
import com.wuzx.enums.SendCodeEnum;
import com.wuzx.service.NotifyService;
import com.wuzx.util.CommonUtil;
import com.wuzx.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/account/v1")
@Slf4j
public class NotifyController {


    @Autowired
    private Producer captchaProducer;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    private static final long CAPTCHA_CODE_EXPIRED = 60 * 1000 * 10;


    /**
     * 生成验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {

        String captchaText = captchaProducer.createText();
        log.info("验证码内容:{}", captchaText);

        //存储redis,配置过期时间 TODO
        redisTemplate.opsForValue().set(getCaptchKey(request), captchaText, CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);

        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            log.error("获取流出错:{}", e.getMessage());
        }

    }

    private String getCaptchKey(HttpServletRequest request) {
        String ip = CommonUtil.getIpAddr(request);
        final String userAgent = request.getHeader("User-Agent");
        String key = "account-service" + CommonUtil.MD5(ip + userAgent);
        log.info("验证码key:{}", key);
        return key;
    }


    /**
     * 测试发送验证码接口-主要是用于对比优化前后区别
     *
     * @return
     */
    @PostMapping("send_code")
    public JsonData sendCode(@RequestBody SendCodeRequest sendCodeRequest, HttpServletRequest request) {
        String key = getCaptchKey(request);
        // redis中保存的验证码
        String cacheCaptcha = redisTemplate.opsForValue().get(key);
        // 前端传过来的验证码
        final String captcha = sendCodeRequest.getCaptcha();

        if (captcha != null && null != cacheCaptcha && captcha.equalsIgnoreCase(cacheCaptcha)) {
            //成功
            redisTemplate.delete(key);
            // 发送短信
            return notifyService.sendCode(SendCodeEnum.USER_REGISTER,sendCodeRequest.getTo());
        } else {
            return JsonData.buildResult(BizCodeEnum.CODE_CAPTCHA_ERROR);
        }

    }


}