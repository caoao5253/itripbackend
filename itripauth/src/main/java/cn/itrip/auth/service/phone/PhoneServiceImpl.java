package cn.itrip.auth.service.phone;

import cn.itrip.auth.service.sms.SmsService;
import cn.itrip.auth.service.user.Itrip_UserService;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.MD5;
import org.springframework.stereotype.Service;
import cn.itrip.common.RedisAPI;
import javax.annotation.Resource;

@Service("phoneService")
public class PhoneServiceImpl implements PhoneService{
    @Resource(name="Itrip_UserService")
    private Itrip_UserService itrip_userService;
    @Resource(name="smsService")
    private SmsService smsService;
    @Resource(name = "redisAPI")
    private RedisAPI redisAPI;

    @Override
    /**
     * 通过手机号完成账号的新增注册功能
     */
    public void insertItripPhone(ItripUser user) throws Exception {
        //1、创建用户,像数据库插入数据
        itrip_userService.insertUser(user);
        //2、生成验证码(1111-9999)
        int code = MD5.getRandomCode();
        //3.发送验证码
        smsService.SendBatchsms(user.getUsercode(), String.valueOf(code));
        //4、缓存验证码到Redis数据库中， 有效期为60秒，测试时可以设置大一点。
        redisAPI.set("activation:" + user.getUsercode(), 60, String.valueOf(code));
    }
    @Override
    /**
     * 手机号码验证并激活用户
     * @phoneNum 手机号
     * @code 验证码
     */
    public boolean validatePhone(String phoneNum, String code) throws Exception {
        String key = "activation:" + phoneNum;//redis缓存关系数据库的key
        String value = redisAPI.get(key);//通过key在redis缓存关系型数据库当中找到验证码
        if (value != null && value.equals(code)) {//判断验证码是否正确
            ItripUser itripUser = itrip_userService.findByUsername(phoneNum);//通过手机号查找相对用的用户数据
            itripUser.setActivated(1);//激活
            itripUser.setFlatid(itripUser.getId());//平台id
            itripUser.setUsertype(0);//自注册用户
            //调用更新方法
            itrip_userService.updateByPrimaryKeySelective(itripUser);
            return true;
        }
        return false;
    }
}
