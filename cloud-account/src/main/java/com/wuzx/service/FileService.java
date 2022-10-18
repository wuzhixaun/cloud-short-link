package com.wuzx.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件接口
 * @author: wuzhixuan
 * @date 2022/10/16 00:25
 * @Version 1.0
 */
public interface FileService {

    String uploadUserImg(MultipartFile file);
}
