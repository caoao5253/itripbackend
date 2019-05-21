package cn.itrip.auth.controller.Login;

import cn.itrip.common.UrlUtils;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/wx/login")
public class WxLoginController {
    //日志对象
    private Logger logger = Logger.getLogger(WxLoginController.class);
    private Logger logger1;

    /**
     * @param code             授权的临时票据
     * @param state            用于保持请求和回调的状态，授权请求后原样带回给第三方。
     *                         该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，
     *                         可设置为简单的随机数加session进行校验。
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/callBackWeChat")
    public void callBackWeChat(@RequestParam String code,
                               @RequestParam String state,
                               HttpServletResponse response) throws Exception {
        logger.info("调用方法成功!");

        /**
         * 1.编写请求code
         * 1.1.请求地址
         * appid=应用唯一标识(网站应用审核通过后，会获取到appid和appsecret)
         * secret=应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
         * code=code参数
         * grant_type=填authorization_code
         */
        String accessUrl="https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=wx860bf23c66d93e33" +
                "&secret=9c92026ab4faa4a4f7ac4cf10b2a8a3c" +
                "&code=" + code +
                "&grant_type=authorization_code";
        //1.2通过发送accessUrl请求地址，并得到返回参数,返回的参数是一个json字符串
        String jsonStr = UrlUtils.loadURL(accessUrl);
        //将json字符串转换成Map集合
        Map<String,String> accessMap= JSON.parseObject(jsonStr, Map.class);
        /**
         * 2.通过code——access_token获取json字符串/accessMap集合中的accessToken,
         * 也就是获取集合中的“接口调用凭证”
         */
        String accessToken = accessMap.get("access_token");

        //3.获取授权用户唯一标识
        String openId=accessMap.get("openid");
        logger.info("accessToken的值为：" + accessToken + ",openId的值为：" + openId);

        /**
         * 4.通过access_token获取用户信息，
         * 参考：资源中心 网站应用微信登录功能授权后接口调用获取用户个人信息（UnionID机制）
         * 4.1 请求URL地址
         */
        String userInfoUrl="https://api.weixin.qq.com/sns/userinfo?access_token="
                            + accessToken +
                            "&openid=" + openId;

        //4.2 发送请求，返回JSON字符串
        String userInfoStr = UrlUtils.loadURL(userInfoUrl);
        Map<String,String> userInfoMap = JSON.parseObject(userInfoStr, Map.class);

        //4.3 获取用户个人信息
        String city = userInfoMap.get("city"); //获取用户信息中的城市
        String nickname = userInfoMap.get("nickname");//昵称微信用户名
        Object sex=userInfoMap.get("sex");
        logger.info("city城市的值为：" + city );
        logger.info("nickname用户名的值为：" + nickname);
        logger.info("sex值为：" + sex );
        logger.info("用户统一标识unionid"+userInfoMap.get("unionid"));
        logger.info("openid"+userInfoMap.get("openid"));
        //重定向网址
        response.sendRedirect("http://www.baidu.com");




    }
}
