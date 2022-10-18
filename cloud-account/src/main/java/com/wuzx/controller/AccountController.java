package com.wuzx.controller;


import com.wuzx.controller.request.AccountRegisterRequest;
import com.wuzx.enums.BizCodeEnum;
import com.wuzx.service.AccountService;
import com.wuzx.service.FileService;
import com.wuzx.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 吴志旋
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private FileService fileService;

    @Autowired
    private AccountService accountService;

    /**
     * 文件上传，最大默认1M
     * 文件格式、扩展名校验
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public JsonData uploadUserImg(@RequestPart("file") MultipartFile file) {
        final String result = fileService.uploadUserImg(file);
        return null == result ? JsonData.buildResult(BizCodeEnum.FILE_UPLOAD_USER_IMG_FAIL) : JsonData.buildSuccess(result);
    }


    /**
     * 用户注册
     * @param registerRequest
     * @return
     */
    @PostMapping("/register")
    public JsonData register(@RequestBody AccountRegisterRequest registerRequest) {
        return accountService.register(registerRequest);
    }
}

