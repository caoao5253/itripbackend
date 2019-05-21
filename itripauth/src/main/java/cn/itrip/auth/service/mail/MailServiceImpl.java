package cn.itrip.auth.service.mail;

import cn.itrip.auth.service.user.Itrip_UserService;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.RedisAPI;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("mailService")
public class MailServiceImpl implements MailService {

    //允许用户快速设置邮件内容的各种属性信息
    // SimpleMailMessage实现了MailMessage接口。
    @Resource
    private SimpleMailMessage activationMailMessage;
    @Resource
    private MailSender mailSender;
    @Resource(name = "redisAPI")
    private RedisAPI redisAPI;
    @Resource(name = "Itrip_UserService")
    private Itrip_UserService itrip_userService;//针对用户的操作业务层
    @Override
    public void sendActivationMail(String mailTo, String activationCode) {
        activationMailMessage.setTo(mailTo);
        activationMailMessage.setText("您的激活码是：" + activationCode);
        mailSender.send(activationMailMessage);

    }
    @Override
    /**
     * 邮箱激活
     * @param mail 邮箱地址
     * @param values 为存入到Redis的激活码所对应的key的value值
     * @return true/false，true表示验证成功
     */
    public boolean activate(String mail, String values) throws Exception {
        //1、验证激活码,在缓存数据库当中查找是否存在
        String value = redisAPI.get("activation:" + mail);
        if (value.equals(values)) {
            ItripUser user = itrip_userService.findByUsername(mail);
            //2、更新用户
            user.setActivated(1);  //激活账户
            user.setUsertype(0); //自注册用户
            user.setFlatid(user.getId());
            itrip_userService.updateByPrimaryKeySelective(user);
            return true;
        } else {
            return false;
        }
    }
}
