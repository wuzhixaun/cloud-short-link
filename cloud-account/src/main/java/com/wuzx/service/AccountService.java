package com.wuzx.service;

import com.wuzx.controller.request.AccountRegisterRequest;
import com.wuzx.model.AccountDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuzx.util.JsonData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 吴志旋
 * @since 2022-10-10
 */
public interface AccountService {

    /**
     * 用户注册
     * @param registerRequest
     * @return
     */
    JsonData register(AccountRegisterRequest registerRequest);
}
