package com.wuzx.service;

import com.wuzx.controller.request.LinkGroupAddRequest;
import com.wuzx.controller.request.LinkGroupUpdateRequest;
import com.wuzx.vo.LinkGroupVO;

import java.util.List;

/**
 * @author: wuzhixuan
 * @date 2022/11/01 00:20
 * @Version 1.0
 */
public interface LinkGroupService {


    /**
     * 创建分组
     * @param addRequest
     * @return
     */
    int add(LinkGroupAddRequest addRequest);

    /**
     * 删除分组
     * @param groupId
     * @return
     */
    int delete(Long groupId);

    /**
     * 详情
     * @param groupId
     * @return
     */
    LinkGroupVO detail(Long groupId);

    /**
     * 获取当前用户所有的锻炼分组
     * @return
     */
    List<LinkGroupVO> listAllGroup();

    /**
     * 修改短链的分组
     * @param request
     * @return
     */
    int updateById(LinkGroupUpdateRequest request);
}
