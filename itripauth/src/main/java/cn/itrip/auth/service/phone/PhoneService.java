package cn.itrip.auth.service.phone;

import cn.itrip.beans.pojo.ItripUser;

public interface PhoneService {
    /**
     * 通过手机号完成账号的新增操作
     */
    public void insertItripPhone(ItripUser user) throws Exception;
    /**
     * @param phoneNum 手机号码
     * @param code     验证码
     * @return true表示验证成功，false表示验证失败。
     * @手机短信验证激活
     */
    public boolean validatePhone(String phoneNum, String code) throws Exception;
}
