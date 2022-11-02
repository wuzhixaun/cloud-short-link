package com.wuzx.service.impl;

import com.wuzx.controller.request.LinkGroupAddRequest;
import com.wuzx.manger.LinkGroupManager;
import com.wuzx.model.LinkGroupDO;
import com.wuzx.model.LoginInterceptor;
import com.wuzx.service.LinkGroupService;
import com.wuzx.vo.LinkGroupVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: wuzhixuan
 * @date 2022/11/01 00:20
 * @Version 1.0
 */
@Service
@Slf4j
public class LinkGroupServiceImpl implements LinkGroupService {


    @Autowired
    private LinkGroupManager linkGroupManager;



    @Override
    public int add(LinkGroupAddRequest addRequest) {
        final long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        final LinkGroupDO linkGroupDO = LinkGroupDO.builder()
                .title(addRequest.getTitle())
                .accountNo(accountNo).build();
        int rows = linkGroupManager.add(linkGroupDO);
        return rows;
    }

    @Override
    public int delete(Long groupId) {
        final long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        return linkGroupManager.delete(accountNo, groupId);
    }

    @Override
    public LinkGroupVO detail(Long groupId) {
        final long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
        LinkGroupDO linkGroupDO = linkGroupManager.detail(groupId, accountNo);
        LinkGroupVO linkGroupVO =LinkGroupVO.builder().build();
        BeanUtils.copyProperties(linkGroupDO, linkGroupVO);
        return linkGroupVO;
    }

    @Override
    public List<LinkGroupVO> listAllGroup() {

        final long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
        List<LinkGroupDO> linkGroupDOList = linkGroupManager.listAllGroup(accountNo);
        final List<LinkGroupVO> groupVOList = linkGroupDOList.stream().map(obj -> {
            LinkGroupVO linkGroupVO = LinkGroupVO.builder().build();
            BeanUtils.copyProperties(obj, linkGroupVO);
            return linkGroupVO;
        }).collect(Collectors.toList());

        return groupVOList;
    }
}
