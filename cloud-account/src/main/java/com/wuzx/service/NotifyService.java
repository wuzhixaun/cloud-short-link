package com.wuzx.service;

import com.wuzx.enums.SendCodeEnum;
import com.wuzx.util.JsonData;

public interface NotifyService {


    JsonData sendCode(SendCodeEnum userRegister, String to);
}

