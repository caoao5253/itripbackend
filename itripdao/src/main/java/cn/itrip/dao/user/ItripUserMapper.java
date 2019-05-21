package cn.itrip.dao.user;

import cn.itrip.beans.pojo.ItripUser;

import java.util.List;
import java.util.Map;

public interface ItripUserMapper {
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
     * @根据参数类型插入用户数据
     */
    void insertItripUser(ItripUser record) throws Exception;

    /**
     * @param record
     * @return
     * @根据参数类型插入用户数据
     */
    public int insertUser(ItripUser record);

    /**
     * @param record
     * @return
     * @根据相关信息更新用户
     */
    int updateByPrimaryKeySelective(ItripUser record);

    public List<ItripUser> getItripUserListByMap(Map<String, Object> param);
}