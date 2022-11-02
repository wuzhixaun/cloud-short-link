package com.wuzx.manger;

import com.wuzx.model.LinkGroupDO;

import java.util.List;

/**
 * @author: wuzhixuan
 * @date 2022/11/02 00:11
 * @Version 1.0
 */
public interface LinkGroupManager {


    /**
     * 新增短链分组
     * @param linkGroupDO
     * @return
     */
    int add(LinkGroupDO linkGroupDO);

    /**
     * 删除短链分组
     * @param accountNo
     * @param groupId
     * @return
     */
    int delete(long accountNo, Long groupId);

    /**
     * 详情
     *
     * @param groupId
     * @param accountNo
     * @return
     */
    LinkGroupDO detail(Long groupId, long accountNo);

    /**
     * 用户分组
     * @param accountNo
     * @return
     */
    List<LinkGroupDO> listAllGroup(long accountNo);
}
