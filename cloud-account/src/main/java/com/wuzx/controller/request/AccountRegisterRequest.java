package com.wuzx.controller.request;

import lombok.Data;

/**
 * @author: wuzhixuan
 * @date 2022/10/19 00:41
 * @Version 1.0
 */
@Data
public class AccountRegisterRequest {

    /**
     * 头像
     */
    private String headImg;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String pwd;



    /**
     * 邮箱
     */
    private String mail;

    /**
     * 用户名
     */
    private String username;


    /**
     * 短信验证码
     *
     *
     * p
     */
    private String code;

}
