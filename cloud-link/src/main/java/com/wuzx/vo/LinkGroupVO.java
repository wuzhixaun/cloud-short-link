 package com.wuzx.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
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
@Builder
public class LinkGroupVO implements Serializable {

    private Long id;

    /**
     * 组名
     */
    private String title;

    /**
     * 账号唯一编
号
     */
    private Long accountNo;

    private Date gmtCreate;

    private Date gmtModified;


}
