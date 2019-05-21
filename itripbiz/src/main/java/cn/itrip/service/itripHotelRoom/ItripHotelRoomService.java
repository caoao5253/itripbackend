package cn.itrip.service.itripHotelRoom;

import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;

import java.util.List;
import java.util.Map;

/**
 * 酒店房间表处理接口
 */
public interface ItripHotelRoomService {
    /**
     * 通过各种条件查询酒店户型(房间)信息
     * @param param
     * @return
     * @throws Exception
     */
    public List<ItripHotelRoomVO> getItripHotelRoomListByMap(Map<String, Object> param)throws Exception;

    /**
     * 通过id获取指定信息
     * @param id
     * @return
     * @throws Exception
     */
    public ItripHotelRoom getItripHotelRoomById(Long id)throws Exception;

}
