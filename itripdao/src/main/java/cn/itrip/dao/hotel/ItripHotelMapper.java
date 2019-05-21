package cn.itrip.dao.hotel;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelMapper {

    /**
     * 通过id查询指定酒店信息
     * @param id
     * @return
     * @throws Exception
     */
    public ItripHotel getItripHotelById(@Param(value = "id") Long id)throws Exception;

    // getItripHotelById 方法到本文档的其他位置进行查找。
    /**
     *  根据酒店ID获取特色及描述
     *  特色表名为:itrip_label_dic 但是因为里面都是相关联的id
     *  也就是说需要从字典表中去查询数据
     *  所以使用通用字典表代替酒店特色表
     * @param id 酒店ID
     */
   public List<ItripLabelDic> getHotelFeatureByHotelId(@Param(value = "id") Long id)throws Exception;


    /**
     *  根据酒店ID获取商圈(城市中发达的商业地段,地段中有多个酒店)
     * @param id 酒店ID
     */
    public List<ItripAreaDic> getHotelAreaByHotelId(@Param(value = "id") Long id)throws Exception;

    /**
     * 根据酒店id查找酒店的设施
     * @param id
     * @return
     * @throws Exception
     */
    public ItripSearchFacilitiesHotelVO getHotelFacilitiesById(@Param(value="id")Long id)throws Exception;

    /**
     * 根据酒店id查找酒店政策
     * @param id
     * @return
     * @throws Exception
     */
    public ItripSearchPolicyHotelVO getHotelhotelPolicyById(@Param(value = "id")Long id)throws  Exception;



}
