package com.wuzx.exception;

import com.wuzx.enums.BizCodeEnum;
import lombok.Data;

@Data
public class BizException extends RuntimeException {

    private int code; // 异常吗
    private String msg;

    public BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }
}
