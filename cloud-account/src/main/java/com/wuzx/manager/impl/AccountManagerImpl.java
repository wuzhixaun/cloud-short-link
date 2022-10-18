package com.wuzx.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuzx.manager.AccountManager;
import com.wuzx.mapper.AccountMapper;
import com.wuzx.model.AccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: wuzhixuan
 * @date 2022/10/19 01:01
 * @Version 1.0
 */
@Component
public class AccountManagerImpl implements AccountManager {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int insert(AccountDO accountDO) {
        return accountMapper.insert(accountDO);
    }


    @Override
    public List<AccountDO> findByPhone(String phone) {
        final List<AccountDO> accountDOList = accountMapper.selectList(new QueryWrapper<AccountDO>().eq("phone", phone));
        return accountDOList;
    }
}
