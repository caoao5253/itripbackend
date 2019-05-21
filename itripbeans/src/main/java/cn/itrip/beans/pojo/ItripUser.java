package cn.itrip.beans.pojo;

import java.util.Date;

public class ItripUser {
    /**
     * 主键
     */
    private Long id;

    /**
     * 若是第三方登录，系统将自动生成唯一账号；自注册用户则为邮箱或者手机号
     */
    private String usercode;

    /**
     * 若是第三方登录，系统将自动生成唯一密码；自注册用户则为自定义密码
     */
    private String userpassword;

    /**
     * 用户类型（标识：0 自注册用户 1 微信登录 2 QQ登录 3 微博登录）
     */
    private Integer usertype;

    /**
     * 平台ID（根据不同登录用户，进行相应存入：自注册用户主键ID、微信ID、QQID、微博ID）
     */
    private Long flatid;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * qq账号
     */
    private String qq;

    /**
     * 微博账号
     */
    private String weibo;

    /**
     * 百度账号
     */
    private String baidu;

    /**
     * 
     */
    private Date creationdate;

    /**
     * 
     */
    private Long createdby;

    /**
     * 
     */
    private Date modifydate;

    /**
     * 
     */
    private Long modifiedby;

    /**
     * 是否激活,(0 false，1 true,默认是0)
     */
    private Integer activated;

    /**
     * 主键
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 若是第三方登录，系统将自动生成唯一账号；自注册用户则为邮箱或者手机号
     * @return userCode 若是第三方登录，系统将自动生成唯一账号；自注册用户则为邮箱或者手机号
     */
    public String getUsercode() {
        return usercode;
    }

    /**
     * 若是第三方登录，系统将自动生成唯一账号；自注册用户则为邮箱或者手机号
     * @param usercode 若是第三方登录，系统将自动生成唯一账号；自注册用户则为邮箱或者手机号
     */
    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    /**
     * 若是第三方登录，系统将自动生成唯一密码；自注册用户则为自定义密码
     * @return userPassword 若是第三方登录，系统将自动生成唯一密码；自注册用户则为自定义密码
     */
    public String getUserpassword() {
        return userpassword;
    }

    /**
     * 若是第三方登录，系统将自动生成唯一密码；自注册用户则为自定义密码
     * @param userpassword 若是第三方登录，系统将自动生成唯一密码；自注册用户则为自定义密码
     */
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    /**
     * 用户类型（标识：0 自注册用户 1 微信登录 2 QQ登录 3 微博登录）
     * @return userType 用户类型（标识：0 自注册用户 1 微信登录 2 QQ登录 3 微博登录）
     */
    public Integer getUsertype() {
        return usertype;
    }

    /**
     * 用户类型（标识：0 自注册用户 1 微信登录 2 QQ登录 3 微博登录）
     * @param usertype 用户类型（标识：0 自注册用户 1 微信登录 2 QQ登录 3 微博登录）
     */
    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    /**
     * 平台ID（根据不同登录用户，进行相应存入：自注册用户主键ID、微信ID、QQID、微博ID）
     * @return flatID 平台ID（根据不同登录用户，进行相应存入：自注册用户主键ID、微信ID、QQID、微博ID）
     */
    public Long getFlatid() {
        return flatid;
    }

    /**
     * 平台ID（根据不同登录用户，进行相应存入：自注册用户主键ID、微信ID、QQID、微博ID）
     * @param flatid 平台ID（根据不同登录用户，进行相应存入：自注册用户主键ID、微信ID、QQID、微博ID）
     */
    public void setFlatid(Long flatid) {
        this.flatid = flatid;
    }

    /**
     * 用户昵称
     * @return userName 用户昵称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户昵称
     * @param username 用户昵称
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 微信号
     * @return weChat 微信号
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 微信号
     * @param wechat 微信号
     */
    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    /**
     * qq账号
     * @return QQ qq账号
     */
    public String getQq() {
        return qq;
    }

    /**
     * qq账号
     * @param qq qq账号
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * 微博账号
     * @return weibo 微博账号
     */
    public String getWeibo() {
        return weibo;
    }

    /**
     * 微博账号
     * @param weibo 微博账号
     */
    public void setWeibo(String weibo) {
        this.weibo = weibo == null ? null : weibo.trim();
    }

    /**
     * 百度账号
     * @return baidu 百度账号
     */
    public String getBaidu() {
        return baidu;
    }

    /**
     * 百度账号
     * @param baidu 百度账号
     */
    public void setBaidu(String baidu) {
        this.baidu = baidu == null ? null : baidu.trim();
    }

    /**
     * 
     * @return creationDate 
     */
    public Date getCreationdate() {
        return creationdate;
    }

    /**
     * 
     * @param creationdate 
     */
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    /**
     * 
     * @return createdBy 
     */
    public Long getCreatedby() {
        return createdby;
    }

    /**
     * 
     * @param createdby 
     */
    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    /**
     * 
     * @return modifyDate 
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * 
     * @param modifydate 
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    /**
     * 
     * @return modifiedBy 
     */
    public Long getModifiedby() {
        return modifiedby;
    }

    /**
     * 
     * @param modifiedby 
     */
    public void setModifiedby(Long modifiedby) {
        this.modifiedby = modifiedby;
    }

    /**
     * 是否激活,(0 false，1 true,默认是0)
     * @return activated 是否激活,(0 false，1 true,默认是0)
     */
    public Integer getActivated() {
        return activated;
    }

    /**
     * 是否激活,(0 false，1 true,默认是0)
     * @param activated 是否激活,(0 false，1 true,默认是0)
     */
    public void setActivated(Integer activated) {
        this.activated = activated;
    }
}