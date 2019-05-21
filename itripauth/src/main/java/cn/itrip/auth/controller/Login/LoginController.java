package cn.itrip.auth.controller.Login;

import cn.itrip.auth.service.sms.SmsService;
import cn.itrip.auth.service.token.TokenService;
import cn.itrip.auth.service.user.Itrip_UserService;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.ItripTokenVO;
import cn.itrip.beans.vo.userinfo.ItripUserVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.ErrorCode;
import cn.itrip.common.MD5;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/api")
public class LoginController {
    @Resource(name = "tokenService")
    private TokenService tokenService;//注入Token业务层
    @Resource(name = "Itrip_UserService")
    private Itrip_UserService itrip_userService;//针对用户的操作业务层


    /**
     * @param name     账号
     * @param password 密码
     * @param request
     * @return
     * @登陆方法 账号密码登陆
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    @ResponseBody
    public Dto login(@RequestParam String name, @RequestParam String password, HttpServletRequest request) {
        try {
            //通过用户名，及加密后的密码去数据库查询指定用户是否存在
            ItripUser itripUser = itrip_userService.login(name, MD5.getMd5(password, 32));
            if (EmptyUtils.isNotEmpty(itripUser)) {//如果对象不为空
                //得到请求头中的User-Agent
                String userAgent = request.getHeader("user-agent");
                //生成Token字符串
                String token = tokenService.generateToken(userAgent, itripUser);
                //保存Token字符串至redis中
                tokenService.save(token, itripUser);
                /**
                 *1.将Token保存至Redis缓存数据库中
                 *2.返回生成好后的token信息。在ItripTokenVO中就保存了Token对象的所有信息。
                 *下面构造方法中，参数一Token字符串对象，参数二有效时间为2小时，参数三生成时间。
                 */
                ItripTokenVO vo = new ItripTokenVO(token,
                        Calendar.getInstance().getTimeInMillis() + 2 * 60 * 60 * 1000,
                        Calendar.getInstance().getTimeInMillis());

                /**
                 * @Calendar.getInstance().getTimeInMillis();返回long型,
                 * @表示从1790-1-1 00:00:00到当前时间总共经过的时间的毫秒数。
                 * @Dto中的字段与Token的数据结构一致。
                 */
                return DtoUtil.returnDataSuccess(vo);
            } else {
                return DtoUtil.returnFail("用户密码不正确，请确认后再输入！", ErrorCode.AUTH_AUTHENTICATION_FAILED);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_AUTHENTICATION_FAILED);
        }
    }

    /**
     * @退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET, headers = "token")
    @ResponseBody
    public Dto logout(HttpServletRequest request) {
        //获取token
        String token = request.getHeader("token");
        //验证token是否存在
        boolean result = tokenService.validate(request.getHeader("user-Agent"), token);
        if (result) {
            tokenService.delete(token);
            return DtoUtil.returnSuccess("退出成功！");
        } else {
            return DtoUtil.returnFail("token无效", ErrorCode.AUTH_TOKEN_INVALID);
        }
    }


    /**
     * 检查用户是否已注册
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "用户名验证", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "验证是否已存在该用户名")
    @RequestMapping(value = "/ckusr", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Dto checkUser(
            @ApiParam(name = "name", value = "被检查的用户名", defaultValue = "test@bdqn.cn")
            @RequestParam String name) {
        try {
/* if(!validEmail(name))
      return  DtoUtil.returnFail("请使用正确的邮箱地址注册",ErrorCode.AUTH_ILLEGAL_USERCODE);*/
            if (null == itrip_userService.findByUsername(name)) {
                return DtoUtil.returnSuccess("用户名可用");
            } else {
                return DtoUtil.returnFail("用户已存在，注册失败", ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
        }
    }


}
