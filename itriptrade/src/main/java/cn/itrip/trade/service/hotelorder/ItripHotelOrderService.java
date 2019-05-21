package cn.itrip.trade.service.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;

import java.util.List;
import java.util.Map;

public interface ItripHotelOrderService {
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
     * 通过判断灵活更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ItripHotelOrder record) throws Exception;
    /**
     * 通过条件查询订单数据
     * @param param
     * @return
     * @throws Exception
     */
    List<ItripHotelOrder> getItripHotelOrderListByMap(Map<String, Object> param) throws Exception;

    /**
     * 通过订单号查询订单
     * @param orderNo
     * @return
     */
    ItripHotelOrder selectCondition(String orderNo);

    /**
     * 判断该订单是否已被处理过（被更新为已支付状态）
     * @param orderNo
     * @throws Exception
     */
    public boolean processed(String orderNo) throws Exception;
    /**
     * 支付成功
     * @param orderNo 订单编号
     * @param payType 支付方式:1:支付宝 2:微信 3:到店付
     * @param tradeNo 支付平台返回的交易码
     * @throws Exception
     */
    public void paySuccess(String orderNo, int payType, String tradeNo) throws Exception;
    /**
     * 支付失败
     * @param orderNo 订单编号
     * @param payType 支付方式:1:支付宝 2:微信 3:到店付
     * @param tradeNo 支付平台返回的交易码
     * @throws Exception
     */
    public void payFailed(String orderNo, int payType, String tradeNo) throws Exception;

}
