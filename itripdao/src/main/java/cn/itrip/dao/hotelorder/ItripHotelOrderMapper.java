package cn.itrip.dao.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripListHotelOrderVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelOrderMapper {
    /**
     * 通过主键id删除指定订单详情
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 这个方法是指定插入该订单表的所有列的数据
     * @param record
     * @return
     */
    int insert(ItripHotelOrder record);

    /**
     * 该方法通过判断数据是否为空来灵活插入数据
     * @param record
     * @return
     */
    int insertSelective(ItripHotelOrder record);

    /**
     * 通过主键插入指定订单信息
     * @param id
     * @return
     */
    ItripHotelOrder selectByPrimaryKey(Long id);

    /**
     * 通过订单号查询订单
     * @param orderNo
     * @return
     */
    ItripHotelOrder selectCondition(String orderNo);
    /**
     * 通过条件查询订单数据
     * @param param
     * @return
     * @throws Exception
     */
    List<ItripHotelOrder> getItripHotelOrderListByMap(Map<String, Object> param) throws Exception;

    int updateByPrimaryKeySelective(ItripHotelOrder record) throws Exception;

    public Integer updateHotelOrderStatus(@Param(value = "id")Long id, @Param(value = "modifiedBy")Long modifiedBy) throws Exception;

    /**
     * 更新
     * @param itripHotelOrder
     * @return
     * @throws Exception
     */
    public Integer updateItripHotelOrder(ItripHotelOrder itripHotelOrder)throws Exception;

    /**
     * 插入
     * @param itripHotelOrder
     * @return
     * @throws Exception
     */
    public Integer insertItripHotelOrder(ItripHotelOrder itripHotelOrder)throws Exception;

    /**
     * 通过id获得指定订单信息
     * @param id
     * @return
     * @throws Exception
     */
    public ItripHotelOrder getItripHotelOrderById(@Param(value = "id") Long id)throws Exception;

    /**
     * 通过订单id查看订单详情-具体房型信息等- add by hanlu
     */
    public ItripPersonalOrderRoomVO getItripHotelOrderRoomInfoById(@Param(value = "id") Long id)throws Exception;
    /***
     * 获取用户的订单数量
     */
    public Integer getOrderCountByMap(Map<String,Object> param)throws Exception;

    /**
     * 获取用户的订单列表 add by hanlu
     */
    public List<ItripListHotelOrderVO> getOrderListByMap(Map<String,Object> param)throws Exception;

}