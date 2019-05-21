package cn.itrip.service.orderlinkuser;

import cn.itrip.beans.vo.order.ItripOrderLinkUserVo;

import java.util.List;
import java.util.Map;

public interface ItripOrderLinkUserService {
    /**
     * 通过相关条件查询订单联系人信息
     * @param param
     * @return
     * @throws Exception
     */
    public List<ItripOrderLinkUserVo> getItripOrderLinkUserListByMap(Map<String, Object> param)throws Exception;
    /**
     * 查询所有未支付的订单所关联的所有常用联系人
     * @return
     */
    public List<Long> getItripOrderLinkUserIdsByOrder() throws Exception;

}
