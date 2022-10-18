package com.wuzx.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.wuzx.config.OSSConfig;
import com.wuzx.service.FileService;
import com.wuzx.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: wuzhixuan
 * @date 2022/10/16 00:26
 * @Version 1.0
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OSSConfig ossConfig;

    @Override
    public String uploadUserImg(MultipartFile file) {
        final String bucketname = ossConfig.getBucketname();
        final String endpoint = ossConfig.getEndpoint();
        final String accessKeyId = ossConfig.getAccessKeyId();
        final String accessKeySecret = ossConfig.getAccessKeySecret();

        // oss客户端构建
        final OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 获取文件原始名称
        final String originalFilename = file.getOriginalFilename();

        // 文件归类 user/year/month

        final LocalDateTime ldt = LocalDateTime.now();
        final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String folder = pattern.format(ldt);
        final String fileName = CommonUtil.generateUUID();
        final String fileExtendFileName = originalFilename.substring(originalFilename.lastIndexOf("."));

        // oss上的bucket创建文件夹
        String newFileName = "/user" + "/" + folder + "/" + fileName + fileExtendFileName;

        // 上传
        try {
            final PutObjectResult putObjectResult = ossClient.putObject(bucketname, newFileName, file.getInputStream());
            if (null != putObjectResult) {
                String imgUrl = "https://oss.wuzx.cool" + newFileName;
                return imgUrl;
            }

        } catch (IOException e) {
            log.error("文件上传失败{}", e.getMessage());
        } finally {
            ossClient.shutdown();
        }

        return null;
    }
}
