package com.wuzx.component;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.wuzx.config.SmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author: wuzhixuan
 * @date 2022/10/12 00:27
 * @Version 1.0
 */

@Component
@Slf4j
public class SmsComponent {

    /**
     * 发送地址
     */
    private static final String URL_TEMPLATE = "https://jmsms.market.alicloudapi.com/sms/send?mobile=%s&templateId=%s&value=%s";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SmsConfig smsConfig;


    /**
     * 发送短信验证码
     * @param to
     * @param templateId
     * @param value
     */
    public void send(String to,String templateId,String value){

        String url = String.format(URL_TEMPLATE,to,templateId,value);
        HttpHeaders headers = new HttpHeaders();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.set("Authorization","APPCODE "+smsConfig.getAppCode());
        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        log.info("url={},body={}",url,response.getBody());
        if(response.getStatusCode().is2xxSuccessful()){
            log.info("发送短信验证码成功");
        }else {
            log.error("发送短信验证码失败:{}",response.getBody());
        }

    }



    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret)
                // 访问的域名
                .setEndpoint("dysmsapi.aliyuncs.com");

        return new Client(config);
    }


    /**
     * 阿里云发送短信验证码
     *
     * @param PhoneNumbers 接收短信的手机号码
     * @param templateCode 短信模板CODE
     * @param value
     */
    public static void sendAli(String PhoneNumbers, String templateCode, String value) {

        // 短信签名名称
        String SignName = "JAVA学习95";


        try {
            final Client client = createClient("xxx", "xxx");
            final SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setSignName(SignName)
                    .setTemplateCode(templateCode)
                    .setTemplateParam(String.format("{\"code\":\"%s\"}", value))
                    .setPhoneNumbers(PhoneNumbers);


            RuntimeOptions runtime = new RuntimeOptions();
            // 复制代码运行请自行打印 API 的返回值
            final SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);


        }catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }


    }

    public static void main(String[] args) {
        sendAli("18702623606","SMS_173251911","666666");
    }



}
