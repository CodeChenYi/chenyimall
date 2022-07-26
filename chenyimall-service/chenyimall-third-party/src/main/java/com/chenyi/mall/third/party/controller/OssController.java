package com.chenyi.mall.third.party.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.alicloud.context.AliCloudProperties;
import com.alibaba.alicloud.context.oss.OssProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping("/oss")
@RestController
public class OssController {

    @Resource
    private OSS ossClient;

    @Value("${spring.cloud.alicloud.bucket}")
    private String bucket;

    @Resource
    private AliCloudProperties aliCloudProperties;

    @Resource
    private OssProperties ossProperties;

    @GetMapping("/uploadPolicy")
    public R uploadPolicy() {

        String accessKey = aliCloudProperties.getAccessKey();
        String endpoint = ossProperties.getEndpoint();

        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//        String callbackUrl = "http://88.88.88.88:8888";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        String dir =  format + "/"; // 用户上传文件时指定的前缀。

        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessKey);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }

        return R.ok().put("data", respMap);
    }

    @DeleteMapping("/deletePolicy")
    public R deletePolicy() {
        String url = "https://mall-chenyi.oss-cn-shenzhen.aliyuncs.com/2022-05-10/113a96a2-6239-4694-87da-fd5a8302b6df_%E5%8D%8E%E4%B8%BAlogo.jpg";
        String endpoint = ossProperties.getEndpoint();
        url = StrUtil.removePrefix(url, "https://" + bucket + "." + endpoint + "/");
        try {
            ossClient.deleteObject(bucket, url);
        } catch (Exception e) {
            return R.error(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        } finally{
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return R.ok();
    }
}
