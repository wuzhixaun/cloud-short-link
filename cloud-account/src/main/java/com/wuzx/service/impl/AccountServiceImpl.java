package com.wuzx.service.impl;

import com.wuzx.controller.request.AccountRegisterRequest;
import com.wuzx.enums.AuthTypeEnum;
import com.wuzx.enums.BizCodeEnum;
import com.wuzx.enums.SendCodeEnum;
import com.wuzx.manager.AccountManager;
import com.wuzx.model.AccountDO;
import com.wuzx.mapper.AccountMapper;
import com.wuzx.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuzx.service.NotifyService;
import com.wuzx.util.CommonUtil;
import com.wuzx.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴志旋
 * @since 2022-10-10
 */
@Slf4j
@Service
public class AccountServiceImpl  implements AccountService {


    @Autowired
    private NotifyService notifyService;

    @Autowired
    private AccountManager accountManager;
    /**
     * 手机验证码验证
     * 密码加密(TODO)
     * 账号唯一性检查(TODO)
     * 插入数据库
     * 新注册用户福利发放(TODO)
     *
     * @param registerRequest
     * @return
     */
    @Override
    public JsonData register(AccountRegisterRequest registerRequest) {
        boolean checkCode = false;

        // 判断验证码
        if (StringUtils.isNoneBlank(registerRequest.getPhone())) {
            checkCode = notifyService.checkCode(SendCodeEnum.USER_REGISTER, registerRequest.getPhone(), registerRequest.getCode());
        }

        // 验证码错误
        if (!checkCode) {
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }

        AccountDO accountDO = new AccountDO();
        BeanUtils.copyProperties(registerRequest, accountDO);
        // 认证级别
        accountDO.setAuth(AuthTypeEnum.DEFAULT.name());

        // 设置密码 秘钥(盐)
        accountDO.setSecret("$1$" + CommonUtil.getStringNumRandom(8));

        String cryptPwd = Md5Crypt.md5Crypt(registerRequest.getPwd().getBytes(), accountDO.getSecret());
        accountDO.setPwd(cryptPwd);

        final int rows = accountManager.insert(accountDO);
        log.info("rows:{},注册成功:{}", rows, accountDO);

        // 用户注册成功，发放福利
        userRegisterInitTask(accountDO);

        return JsonData.buildSuccess();
    }

    /**
     * 用户初始化，发放福利 TODO
     *
     * @param accountDO
     */
    private void userRegisterInitTask(AccountDO accountDO) {


    }
}
