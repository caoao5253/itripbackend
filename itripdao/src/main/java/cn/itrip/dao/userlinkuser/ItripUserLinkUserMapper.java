package cn.itrip.dao.userlinkuser;

import cn.itrip.beans.pojo.ItripUserLinkUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripUserLinkUserMapper {
    public Integer updateItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception;

    public Integer deleteItripUserLinkUserByIds(@Param(value = "ids") Long[] ids)throws Exception;

    /**
     * 添加常用联系人
     * @param itripUserLinkUser
     * @return
     * @throws Exception
     */
    public Integer insertItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception;

    /**
     * 根据相关条件查询用户的联系人列表
     * @param param
     * @return
     * @throws Exception
     */
    public List<ItripUserLinkUser> getItripUserLinkUserListByMap(Map<String, Object> param)throws Exception;
}
