package cn.itrip.auth.service.token;

import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import com.alibaba.fastjson.JSON;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    @Resource(name = "redisAPI")
    private RedisAPI redisAPI;

    //按指定的格式生成Token，token:客户端标识-USERCODE-USERID-CREATIONDATE-RANDEM[6位]
    //生成token(格式为token:设备-加密的用户名-时间-六位随机数)
    @Override
    public String generateToken(String userAgent, ItripUser user) {
        //创建字符串工具，加头部token:
        StringBuilder str = new StringBuilder("token:");
        //1. User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头。 注：在浏览器中按F12NetWorkHeaders可以看到User-Agent
        // Java中通过浏览器请求头（User-Agent）获取 浏览器类型，操作系统类型，手机机型。
        /**
         * 3. Browser browser = userAgent.getBrowser();  获取浏览器。
         * 4. OperatingSystem os = userAgent.getOperatingSystem();  获取操作系统。
         */
        //2.通过请求头得到用户代理对象。
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        if (agent.getOperatingSystem().isMobileDevice()) {
            //加设备:当前用户的操作系统是移动端。
            str.append("MOBILE-");
        } else {
            //或者是PC端
            str.append("PC-");
        }
        //加唯一账号
        str.append(MD5.getMd5(user.getUsercode(), 32) + "-");
        //加ID
        str.append(user.getId().toString() + "-");
        //加时间
        str.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-");
        //加把请求头加密后的6位随机数
        str.append(MD5.getMd5(userAgent, 6));
        /**
         * 返回后为:token::PC-b7782e510d27f86dd1ba7c0c915c2351-12-20190403110038-e84374
         * 这样类似的数据 一个一个对齐
         */
        return str.toString();//返回Token字符串
    }

    //将Token保存至Redis缓存数据库中
    @Override
    public void save(String token, ItripUser user) {
        if (token.startsWith("token:PC-")) {  //判断是否是PC端, 有效期是以【秒】为单位。
            redisAPI.set(token, 2 * 60 * 60, JSON.toJSONString(user));
        } else {  //否则是移动端, 当不设置有效期，默认是一直有效,一直存在.
            redisAPI.set(token, JSON.toJSONString(user));
        }
    }

    //验证Token
    @Override
    /**
     * userAgent：请求头
     * token:生成的token字符串
     */
    public boolean validate(String userAgent, String token) {
        //判断Key值是否存在
        if (!redisAPI.exist(token)) {
            return false;  //验证失败
        }
        /**
         * 按"-"进行分割后取第四位，即得到使用MD5对http请求中的user-agent加密，生成的六位随机数。
         * token:客户端标识-USERCODE-USERID-CREATIONDATE-RANDEM[6位]
         * token::PC-b7782e510d27f86dd1ba7c0c915c2351-12-20190403110038-e84374
         */
        String agentMD5 = token.split("-")[4];
        //将未加密的请求头UserAgent加密后去和token字符串里已经被加密后的请求头进行判断
        //不用去验证token是否在有效期中，首先如果是PC端的用户，token有效期为2h，在2h后在redis中会被自动删除。
        //然后在移动端的用户信息是永久有效到redis缓存数据库中。
        if (MD5.getMd5(userAgent, 6).equals(agentMD5)) {
            return true;//说明请求头是相同的
        }
        return false;
    }

    @Override
    /**
     * 退出后通过token字符串删除token
     */
    public void delete(String token) {
        redisAPI.delete(token);
    }

    private long expireTime = 2 * 60 * 60 * 1000;   /*2小时有效期, 毫秒为单位*/
    private int delay = 2 * 60;  //2分钟，以秒为单位
    private int friTime = 5 * 60 * 1000;  //5分钟,，以毫秒为单位。

    @Override
    /**
     * Token时间置换,新时间替换掉旧的
     */
    public String reloadToken(String userAgent, String token) throws Exception {
        //1、验证token是否有效
        if (!redisAPI.exist(token)) {
            throw new Exception("token无效");
        }
        //2、看能不能置换,规则：用当前时间减token生成时间得到值，看这个值是否大于30分钟，大于则可以。
        //  token:客户端标识-USERCODE-USERID-CREATIONDATE-RANDEM[6位]
        //获取token的生成时间
        Date genTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(token.split("-")[3]);

        long activeTime = genTime.getTime()+ expireTime;  //token的有效期，规则生成时间+2小时
        //使用当前时间去减token的生成时间
        long passed = activeTime - Calendar.getInstance().getTimeInMillis(); //过期时间-当前系统时间
        if (passed > friTime) {  //过期时间-当前时间>5分钟，如果大于则不进行置换。
            throw new Exception("处在token置换保护期内，不能置换！剩余时间为：" + passed / 1000 + "s");
        }
        /**
         *3、进行置换, 之前是将ItripUser用户对象转换成JSON字符串并保存到Redis中(value)
         *【key为redis字符串】。下面代码是将取出来的JSON字符串转换成ItripUser用户对象。
         **/
        String userString = redisAPI.get(token); //拿到用户字符串
        ItripUser user = JSON.parseObject(userString, ItripUser.class);
        //生成新的Token
        String newToken = generateToken(userAgent, user);
        //4、老的token进行延时过期(估计是怕来不急，所以延时一会不让它被redis删除)
        redisAPI.set(token, delay, JSON.toJSONString(userString));
        //5、新的token保存至Redis缓存数据库
        this.save(newToken, user);

        return newToken;
    }
}

