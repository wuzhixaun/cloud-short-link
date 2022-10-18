package com.wuzx.controller.request;

import lombok.Data;

/**
 * @author: wuzhixuan
 * @date 2022/10/19 01:24
 * @Version 1.0
 */
@Data
public class AccountLoginRequest {

    private String phone;

    private String pwd;
}
