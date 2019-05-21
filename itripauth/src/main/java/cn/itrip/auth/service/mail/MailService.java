package cn.itrip.auth.service.mail;

import org.springframework.stereotype.Service;


public interface MailService {
    /**
     * 发送包括激活码的邮件，用于激活用户账号
     * @param mailTo 收件人的邮箱地址
     * @param activationCode 验证码
     */
    public void sendActivationMail(String mailTo,String activationCode);

    /**邮箱验证
     * @param mail 邮箱地址
     * @param code 为存入到Redis的激活码所对应的key。格式是：activation: + 验证码
     * @return true/false，true表示验证成功
     * @邮箱验证激活
     */
    public boolean activate(String mail, String code) throws Exception;
}
