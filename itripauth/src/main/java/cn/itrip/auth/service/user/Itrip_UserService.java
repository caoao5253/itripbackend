package cn.itrip.auth.service.user;

import cn.itrip.beans.pojo.ItripUser;

public interface Itrip_UserService {
    /**
     * @param id
     * @return
     * @根据主键id查询指定用户信息
     */
    ItripUser selectByPrimaryKey(Long id);

    /**
     * @param id
     * @return
     * @根据主键id删除用户
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @param record
     * @return
     * @根据相关信息更新用户
     */
    int updateByPrimaryKeySelective(ItripUser record);

    /**
     * 根据账号密码查找指定账号
     *
     * @param userCode
     * @param userPassword
     * @return
     * @throws Exception
     */
    public ItripUser login(String userCode, String userPassword) throws Exception;
    /**
     * @param record
     * @return
     * @根据参数类型插入用户数据 /**
     * * 邮箱注册调用得插入方法。
     * * 1.生成邮箱激活码得时候将用户信息注入到用户表中
     * * 2.生成激活码
     * * 3.将激活码发送到邮箱地址当中
     * * 4.将激活码存入Redis缓存数据库当中以备验证激活码发送的激活码是否正确
     */
    void insertItripUser(ItripUser record) throws Exception;

    /**
     * @param record
     * @return
     * @根据参数类型插入用户数据
     */
    public int insertUser(ItripUser record);


    /**
     * @param username
     * @根据用户名查找用户
     */
    public ItripUser findByUsername(String username);






}
