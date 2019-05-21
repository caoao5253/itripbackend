package cn.itrip.service.userlinkuser;

import cn.itrip.beans.pojo.ItripUserLinkUser;

import java.util.List;
import java.util.Map;

public interface ItripUserLinkUserService {
    /**
     * 通过相关条件修改联系人
     * @param itripUserLinkUser
     * @return
     * @throws Exception
     */
    public Integer modifyItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception;

    /**
     * 通过id数组删除多个联系人
     * @param ids
     * @return
     * @throws Exception
     */
    public Integer deleteItripUserLinkUserByIds(Long[] ids)throws Exception;

    /**
     * 添加常用联系人
     * @param itripUserLinkUser
     * @return
     * @throws Exception
     */
    public Integer addItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception;

    /**
     * 根据相关条件查询用户联系人列表
     * @param param
     * @return
     * @throws Exception
     */
    public List<ItripUserLinkUser> getItripUserLinkUserListByMap(Map<String, Object> param)throws Exception;
}
