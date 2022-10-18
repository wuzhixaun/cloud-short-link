package com.wuzx.service;

import com.wuzx.enums.SendCodeEnum;
import com.wuzx.util.JsonData;

public interface NotifyService {


    /**
     * 发送验证码
     *
     * @param userRegister
     * @param to
     * @return
     */
    JsonData sendCode(SendCodeEnum userRegister, String to);


    /**
     * 校验验证码
     * @param userRegister
     * @param to
     * @param code
     * @return
     */
    boolean checkCode(SendCodeEnum userRegister, String to, String code);
}

