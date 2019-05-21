package cn.itrip.auth.service.sms;

public interface SmsService {

    /**
     * 我们使用的是SendSms
     * SendSms接口是短信发送接口，支持在一次请求中向多个不同的手机号码发送同样短信模板内容的短信。
     * 如果您需要在一次请求中分别向多个不同的手机号码发送不同签名和模版内容的短信，请使用SendBatchSms接口。
     * @param phone         接受的手机号的地址   String格式
     * @param code          验证码 json格式     [{"占位符名":"验证码"}]
     * @throws Exception
     */
    public void SendBatchsms(String phone,String code)throws  Exception;
}
