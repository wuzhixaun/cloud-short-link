package com.wuzx.controller.request;

import lombok.Data;

/**
 * @author: wuzhixuan
 * @date 2022/11/25 00:06
 * @Version 1.0
 */
@Data
public class LinkGroupUpdateRequest {

    /**
     * 组id
     */
    private Long id;
    /**
     * 组名
     */
    private String title;
}
