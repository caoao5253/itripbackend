package cn.itrip.trade.service.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.common.EmptyUtils;
import cn.itrip.dao.hotelorder.ItripHotelOrderMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单支付处理实现
 */
@Service("itripHotelOrderService")
public class ItripHotelOrderServiceImpl implements ItripHotelOrderService {
    @Resource(name = "itripHotelOrderMapper")
    private ItripHotelOrderMapper itripHotelOrderMapper;
    private Logger logger = Logger.getLogger(ItripHotelOrderServiceImpl.class);

    /**
     * @param id
     * @return
     * @通过id查询酒店订单信息
     */
    public ItripHotelOrder selectByPrimaryKey(Long id) {
        logger.debug("加载订单:" + id);
        Map<String, Object> param = new HashMap();
        param.put("orderNo", id);
        try {
            //通过条件查询订单列表数据
            List<ItripHotelOrder> orders = getItripHotelOrderListByMap(param);
            if (orders.size() == 1) {//存在数据
                return orders.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param record
     * @return
     * @插入表中所有列的数据，所有列都要有数据才能插入
     */
    public int insert(ItripHotelOrder record) {
        return 0;
    }

    /**
     * @param id
     * @return
     * @通过id删除订单
     */
    public int deleteByPrimaryKey(Long id) {
        return itripHotelOrderMapper.deleteByPrimaryKey(id);
    }

    /**
     * @param record
     * @return
     * @通过判断插入需要的数据，灵活插入
     */
    public int insertSelective(ItripHotelOrder record) {
        return itripHotelOrderMapper.insertSelective(record);
    }


    /**
     * @param record
     * @return
     * @ 灵活判断哪些数据更新
     */
    public int updateByPrimaryKeySelective(ItripHotelOrder record) throws Exception {
        return itripHotelOrderMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param param
     * @return
     * @throws Exception
     * @通过条件查询订单数据列表
     */
    public List<ItripHotelOrder> getItripHotelOrderListByMap(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.getItripHotelOrderListByMap(param);
    }

    public ItripHotelOrder selectCondition(String orderNo) {
        return itripHotelOrderMapper.selectCondition(orderNo);
    }

    /**
     * @param orderNo
     * @throws Exception
     * @判断该订单是否已被处理过（已被更新为已支付状态）
     */
    public boolean processed(String orderNo) throws Exception {
        //通过商品id查询指定商品的信息
        ItripHotelOrder itripHotelOrder = this.selectCondition(orderNo);
        /**
         * 判断是否支付成功
         * 订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
         */
        return itripHotelOrder.getOrderstatus().equals(2) &&
                !EmptyUtils.isEmpty(itripHotelOrder.getTradeno());
    }

    /**
     * @param orderNo 订单编号
     * @param payType 支付方式:1:支付宝 2:微信 3:到店付
     * @param tradeNo 支付平台返回的交易码，交易号  支付宝：交易号   微信：预支付交易会话标识
     * @throws Exception
     * @支付成功
     */
    public void paySuccess(String orderNo, int payType, String tradeNo) throws Exception {
        //更新订单状态、支付宝交易号
        logger.debug("订单支付成功：" + orderNo);
        ItripHotelOrder itripHotelOrder = this.selectCondition(orderNo);
        itripHotelOrder.setOrderstatus(2);//支付成功
        itripHotelOrder.setPaytype(payType);//支付方式 1:支付宝 2:微信 3:到店付
        itripHotelOrder.setTradeno(tradeNo);//交易号（如支付宝交易号） 微信：预支付交易会话标识
        //更新支付状态
        itripHotelOrderMapper.updateByPrimaryKeySelective(itripHotelOrder);

        //支付成功后，能减库存，由业务提供API，在这里进行调用(后续代码不写)
        //订单状态0: 表示未支付  1: 表示订单已取消  2: 表示支付成功3: 表示支付失败   //只有0和3的订单可以再进行支付。
    }

    /**
     * @param orderNo 订单编号
     * @param payType 支付方式:1:支付宝 2:微信 3:到店付
     * @param tradeNo 支付平台返回的交易码
     * @throws Exception
     * @ 支付失败
     */
    public void payFailed(String orderNo, int payType, String tradeNo) throws Exception {
        logger.debug("订单支付失败：" + orderNo);
        ItripHotelOrder itripHotelOrder =  this.selectCondition(orderNo);
        itripHotelOrder.setOrderstatus(1);//支付状态：已取消
        itripHotelOrder.setPaytype(payType);//支付方式 1:支付宝 2:微信 3:到店付
        itripHotelOrder.setTradeno(tradeNo);//交易号（如支付宝交易号）
        itripHotelOrderMapper.updateByPrimaryKeySelective(itripHotelOrder);
    }


}
