package com.wuzx.controller.request;

import lombok.Data;

/**
 * 验证码
 *
 * @author: wuzhixuan
 * @date 2022/10/14 00:22
 * @Version 1.0
 */
@Data
public class SendCodeRequest {

    // 验证码
    private String captcha;

    // 接受人
    private String to;
}
