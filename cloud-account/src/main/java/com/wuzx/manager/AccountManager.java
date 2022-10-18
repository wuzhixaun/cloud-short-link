package com.wuzx.manager;

import com.wuzx.model.AccountDO;

import java.util.List;

/**
 * @author: wuzhixuan
 * @date 2022/10/19 01:00
 * @Version 1.0
 */
public interface AccountManager {

    int insert(AccountDO accountDO);

    List<AccountDO> findByPhone(String phone);
}
