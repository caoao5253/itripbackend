package cn.itrip.auth.service.sms;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;

@Service("smsService")
public class SmsServiceImpl implements SmsService {
    @Override
    /**
     * 我们使用的是SendSms
     * SendSms接口是短信发送接口，支持在一次请求中向多个不同的手机号码发送同样短信模板内容的短信。
     * 如果您需要在一次请求中分别向多个不同的手机号码发送不同签名和模版内容的短信，请使用SendBatchSms接口。
     * @param phone         接受的手机号的地址   String格式,多个使用英文逗号隔开，上限为1000个
     * @param code          验证码 json格式     [{"占位符名":"验证码"}]
     * @throws Exception
     */
    public void SendBatchsms(String phone, String code) throws Exception {

        /**
         * 第一个参数是默认
         * 第二个是子用户的AccessKey Id
         * 第三个是自用户的AccessKey Sercet
         */
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI55y7UyYODo1E", "LrbKcMzIxQSDugdYfFKQb4VanLlPaz");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");//服务地址 这个是用来发送短信的地址
        request.setVersion("2017-05-25");//API 的版本号
        request.setAction("SendSms");//调用的API接口
        /**
         * 因为我们调用的是短信API也就是SendSms，下方CommonRequest对象
         * 公共请求参数设置了其Action属性也就是API接口，短信的RegionId值为:cn-hangzhou
         */
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);//发送手机地址
        request.putQueryParameter("SignName", "a旅游");//短信签名
        request.putQueryParameter("TemplateCode", "SMS_163055026");//短信模板Code
        request.putQueryParameter("TemplateParam", "{code:"+code+"}");//验证码 SendSms只支持一个占位符参数
        try {
            CommonResponse response = client.getCommonResponse(request);
            //输出状态
            System.out.println(JSONObject.parseObject(response.getData()));
            if(!JSONObject.parseObject(response.getData()).getString("Message").equals("OK")){
                throw  new Exception("短息发送失败!");
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
