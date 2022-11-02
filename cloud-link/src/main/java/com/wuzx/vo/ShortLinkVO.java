package com.wuzx.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 吴志旋
 * @since 2022-11-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShortLinkVO implements Serializable {


    private Long id;

    /**
     * 组
     */
    private Long groupId;

    /**
     * 短链标题
     */
    private String title;

    /**
     * 原始url地址
     */
    private String originalUrl;

    /**
     * 短链域名
     */
    private String domain;

    /**
     * 短链压缩码
     */
    private String code;

    /**
     * ⻓链的md5码，方便查找
     */
    private String sign;

    /**
     * 过期时间， ⻓久就是-1
     */
    private Date expired;

    /**
     * 账号唯一编 号
     */
    private Long accountNo;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 0是默认，1是删 除
     */
    private Integer del;

    /**
     * 状态，lock是锁定不可 用，active是可用
     */
    private String state;

    /**
     * 链接产品层 级:FIRST 免费⻘铜、SECOND⻩金、THIRD钻石
     */
    private String linkType;


}
