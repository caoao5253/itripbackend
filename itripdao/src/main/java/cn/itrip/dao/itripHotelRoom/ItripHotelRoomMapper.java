package cn.itrip.dao.itripHotelRoom;

import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelRoomMapper {
    /**
     * 通过各种条件查询酒店户型(房间)信息
     * @param param
     * @return
     * @throws Exception
     */
    public List<ItripHotelRoomVO> getItripHotelRoomListByMap(Map<String, Object> param)throws Exception;

    public ItripHotelRoom getItripHotelRoomById(@Param(value = "id") Long id)throws Exception;
}
