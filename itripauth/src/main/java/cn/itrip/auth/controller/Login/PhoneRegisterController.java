package cn.itrip.auth.controller.Login;

import cn.itrip.auth.service.phone.PhoneService;
import cn.itrip.auth.service.user.Itrip_UserService;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.userinfo.ItripUserVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ErrorCode;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/api")
public class PhoneRegisterController {
    @Resource(name = "Itrip_UserService")
    private Itrip_UserService itrip_userService;//针对用户的操作业务层
    @Resource(name = "phoneService")
    private PhoneService phoneService;
    @Resource(name = "redisAPI")
    private RedisAPI redisAPI;

    /**
     * @param userVO
     * @使用手机注册
     */
    @ApiOperation(value = "使用手机注册", httpMethod = "POST",
            protocols = "HTTP", response = Dto.class, notes = "使用手机注册 ")
    @RequestMapping(value = "/doRegisterByPhone", method = RequestMethod.POST)
    @ResponseBody
    public Dto doRegisterByPhone(@RequestBody ItripUserVO userVO) {
        /**
         * ItripUserVO这个是公共类,userCode属性：如果是邮箱注册那么账号就是邮箱，
         * 如果是手机注册那么写的就是手机号
         * 验证手机号是否匹配规则
         */
        if (!this.validPhone(userVO.getUserCode())) {
            return DtoUtil.returnFail("请使用正确的手机号码！", ErrorCode.AUTH_ILLEGAL_USERCODE);
        }
        //查看手机号是否存在
        try {
            if (null == itrip_userService.findByUsername(userVO.getUserCode())) {
                ItripUser user = new ItripUser();
                user.setUsercode(userVO.getUserCode());//账号 邮箱或者手机号或者第三方登陆自动生成
                user.setUserpassword(MD5.getMd5(userVO.getUserPassword(), 32));//加密后的密码
                user.setUsertype(0); //账号为手机  0是自己注册
                user.setUsername(userVO.getUserName());
                phoneService.insertItripPhone(user);//通过手机进行插入
                return DtoUtil.returnSuccess();
            } else {
                return DtoUtil.returnFail("用户已存在，注册失败", ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
        }
    }


    @ApiOperation(value = "手机注册用户激活", httpMethod = "PUT",
            protocols = "HTTP", response = Dto.class, notes = "手机注册用户激活")
    @RequestMapping(value = "/activateByPhone", method = RequestMethod.PUT)
    @ResponseBody
    public Dto activateByPhone(
            @ApiParam(name = "phone", value = "手机号码") @RequestParam String phone,
            @ApiParam(name = "code", value = "激活码") @RequestParam String code) {
        try {
            if (phoneService.validatePhone(phone, code)) {
                return DtoUtil.returnSuccess("激活成功");
            } else {
                return DtoUtil.returnSuccess("激活失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("激活失败", ErrorCode.AUTH_ACTIVATE_FAILED);
        }

    }

    /**
     * @param phone 手机号码
     * @return 返回true表示手机号码验证通过。否则返回false
     * @验证手机号码的格式是否正确
     */
    public boolean validPhone(String phone) {
        String regex = "^1[34578]{1}\\d{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }

}
