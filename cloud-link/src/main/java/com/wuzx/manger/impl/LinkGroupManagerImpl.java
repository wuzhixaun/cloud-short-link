package com.wuzx.manger.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuzx.manger.LinkGroupManager;
import com.wuzx.mapper.LinkGroupMapper;
import com.wuzx.model.LinkGroupDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: wuzhixuan
 * @date 2022/11/02 00:14
 * @Version 1.0
 */
@Component
@Slf4j
public class LinkGroupManagerImpl implements LinkGroupManager {

    @Autowired
    private LinkGroupMapper linkGroupMapper;
    @Override
    public int add(LinkGroupDO linkGroupDO) {
        return linkGroupMapper.insert(linkGroupDO);
    }


    @Override
    public int delete(long accountNo, Long groupId) {
        return linkGroupMapper.delete(new QueryWrapper<LinkGroupDO>()
                .eq("account_no", accountNo)
                .eq("id", groupId));
    }

    @Override
    public LinkGroupDO detail(Long groupId, long accountNo) {
        return linkGroupMapper.selectOne(new QueryWrapper<LinkGroupDO>()
                .eq("account_no", accountNo)
                .eq("id", groupId));
    }

    @Override
    public List<LinkGroupDO> listAllGroup(long accountNo) {
        return linkGroupMapper.selectList(new QueryWrapper<LinkGroupDO>()
                .eq("account_no", accountNo));
    }
}
