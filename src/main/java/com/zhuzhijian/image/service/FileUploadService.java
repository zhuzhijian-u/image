package com.zhuzhijian.image.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.zhuzhijian.image.config.AliOSSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FileUploadService {
    /**
     * 斜杠
     */
    private final String FLAG_SLANTING_ROD = "/";
    /**
     * http://
     */
    private final String FLAG_HTTP = "http://";
    /**
     * https://
     */
    private final String FLAG_HTTPS = "https://";
    /**
     * 空字符串
     */
    private final String FLAG_EMPTY_STRING = "";
    /**
     * 点号
     */
    private final String FLAG_DOT = ".";
    /**
     * 横杠
     */
    private final String FLAG_CROSSBAR = "-";

    @Autowired
    private AliOSSConfig aliOSSConfig;

    public String upload(String fileName, String filePath, InputStream inputStream) {

        String fileUrl;
        OSSClient ossClient = null;
        try{
            ossClient = new OSSClient(aliOSSConfig.getEndpoint(), aliOSSConfig.getKeyid(), aliOSSConfig.getKeysecret());

            // File url
            StringBuilder buffer = new StringBuilder();
            buffer.append(filePath).append(fileName);
            fileUrl = buffer.toString();
            // Upload file and set ACL
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(aliOSSConfig.getBucketname(), fileUrl, inputStream));
            ossClient.setBucketAcl(aliOSSConfig.getBucketname(), CannedAccessControlList.PublicRead);
            if(result != null) {
//                log.info("Upload result:{}", result.getETag());
//                log.info("Upload file {} successfully.", fileName);
            }
            fileUrl = getHostUrl().concat(fileUrl);


        }catch (Exception e){
            fileUrl = null;
        }finally {
            if(ossClient != null) {
                ossClient.shutdown();
            }
        }
        return fileUrl;
    }

    private String getHostUrl() {
        String hostUrl = null;
        if(aliOSSConfig.getEndpoint().startsWith(FLAG_HTTP)) {
            hostUrl = FLAG_HTTP.concat(aliOSSConfig.getBucketname()).concat(FLAG_DOT)
                    .concat(aliOSSConfig.getEndpoint().replace(FLAG_HTTP, FLAG_EMPTY_STRING)).concat(FLAG_SLANTING_ROD);
        } else if (aliOSSConfig.getEndpoint().startsWith(FLAG_HTTPS)) {
            return FLAG_HTTPS.concat(aliOSSConfig.getBucketname()).concat(FLAG_DOT)
                    .concat(aliOSSConfig.getEndpoint().replace(FLAG_HTTPS, FLAG_EMPTY_STRING)).concat(FLAG_SLANTING_ROD);
        }
        return hostUrl;
    }
}
