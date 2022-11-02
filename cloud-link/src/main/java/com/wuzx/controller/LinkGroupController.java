package com.wuzx.controller;


import com.wuzx.controller.request.LinkGroupAddRequest;
import com.wuzx.enums.BizCodeEnum;
import com.wuzx.service.LinkGroupService;
import com.wuzx.util.JsonData;
import com.wuzx.vo.LinkGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 吴志旋
 * @since 2022-11-01
 */
@RestController
@RequestMapping("/api/group/v1")
public class LinkGroupController {

    @Autowired
    private LinkGroupService linkGroupService;

    /**
     * 新增分组
     *
     * @param addRequest
     * @return
     */
    @PostMapping("/add")
    public JsonData add(@RequestBody LinkGroupAddRequest addRequest) {
        int rows = linkGroupService.add(addRequest);
        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildResult(BizCodeEnum.GROUP_ADD_FAIL);
    }


    /**
     * 删除短链分组
     *
     * @param groupId
     * @return
     */
    @DeleteMapping("/del/{group_id}")
    public JsonData delete(@PathVariable("group_id") Long groupId) {
        int rows = linkGroupService.delete(groupId);
        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildResult(BizCodeEnum.GROUP_DEL_FAIL);
    }


    /**
     * 根据id找详情
     *
     * @param groupId
     * @return
     */
    @GetMapping("/detail/{group_id}")
    public JsonData detail(@PathVariable("group_id") Long groupId) {
        LinkGroupVO linkGroupVO = linkGroupService.detail(groupId);
        return JsonData.buildSuccess(linkGroupVO);
    }

    @GetMapping("/list")
    public JsonData findUserAllLinkGroup() {
        List<LinkGroupVO> list = linkGroupService.listAllGroup();
        return JsonData.buildSuccess(list);
    }


}
