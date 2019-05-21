package cn.itrip.auth.service.user;

import cn.itrip.auth.service.mail.MailService;
import cn.itrip.auth.service.sms.SmsService;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import cn.itrip.dao.user.ItripUserMapper;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("Itrip_UserService")
public class Itrip_UserServiceImpl implements Itrip_UserService {
    @Resource(name = "itripUserMapper")
    private ItripUserMapper itripUserMapper;
    @Resource(name = "mailService")
    private MailService mailService;
    @Resource(name = "redisAPI")
    private RedisAPI redisAPI;
    @Resource(name = "smsService")
    private SmsService smsService;//短信处理业务层

    /**
     * @param id
     * @return
     * @根据主键id查询指定用户信息
     */
    @Override
    public ItripUser selectByPrimaryKey(Long id) {
        return itripUserMapper.selectByPrimaryKey(id);
    }
    /**
     * @param id
     * @return
     * @根据主键id删除用户
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return itripUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * @param record
     * @return
     * @根据相关信息更新用户
     */
    @Override
    public int updateByPrimaryKeySelective(ItripUser record) {
        return itripUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    /**
     * 通过账号密码登陆
     */
    public ItripUser login(String userCode, String userPassword) throws Exception {
        //通过账号去查找是否存在相对应的账号
        ItripUser user = this.findByUsername(userCode);
        //账号存在后判断账号是否存在及其密码是否匹配
        if (null != user && user.getUserpassword().equals(userPassword)) {
            if (user.getActivated() != 1) {
                throw new Exception("账号尚未激活！");
            }
            return user;
        } else {
            return null;
        }
    }

    /**
     * @param userCode
     * @根据账号查找是否存在
     */
    public ItripUser findByUsername(String userCode) {
        //创建Map集合对象
        Map<String, Object> param = new HashMap();
        //存入账号
        param.put("userCode", userCode);
        //查询
        List<ItripUser> list = itripUserMapper.getItripUserListByMap(param);
        //数据库中账号是唯一的 所以只需判断是否存在即可
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @Override
    /**
     * 邮箱注册调用得插入方法。
     * 1.生成邮箱激活码得时候将用户信息注入到用户表中
     * 2.生成激活码
     * 3.将激活码发送到邮箱地址当中
     * 4.将激活码存入Redis缓存数据库当中以备验证激活码发送的激活码是否正确
     */
    public void insertItripUser(ItripUser record) throws Exception {
        //1、添加用户
        itripUserMapper.insertItripUser(record);
        //2、生成激活码， new Date().toLocaleString()生成日期和时间，
        //格式如：2019-1-25 22:56:32，然后进行32位的MD5加密
        String activationCode = MD5.getMd5(new Date().toLocaleString(), 32);
        //3、发送邮件,通过注册好的用户信息当中的邮箱账号接收邮件
        mailService.sendActivationMail(record.getUsercode(), activationCode);
        //4、激活码存入Redis中 保存时间为5分钟
        redisAPI.set("activation:" + record.getUsercode(), 5 * 60, activationCode);
    }

    /**
     * 插入数据并返回插入后的状态  1:返回  0:插入失败
     * 根据参数类型进行插入数据
     */
    @Override
    public int insertUser(ItripUser record) {
        return itripUserMapper.insertUser(record);
    }






}
