package com.wuzx.service.impl;

import com.wuzx.model.AccountDO;
import com.wuzx.mapper.AccountMapper;
import com.wuzx.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴志旋
 * @since 2022-10-10
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountDO> implements AccountService {

}
