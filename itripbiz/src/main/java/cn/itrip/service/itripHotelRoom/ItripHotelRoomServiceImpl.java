package cn.itrip.service.itripHotelRoom;

import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import cn.itrip.dao.itripHotelRoom.ItripHotelRoomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("itripHotelRoomService")
public class ItripHotelRoomServiceImpl implements  ItripHotelRoomService {

    @Resource
    private ItripHotelRoomMapper itripHotelRoomMapper;
    /**
     * 通过各种条件查询酒店户型(房间)信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public List<ItripHotelRoomVO> getItripHotelRoomListByMap(Map<String, Object> param) throws Exception {
        return itripHotelRoomMapper.getItripHotelRoomListByMap(param);
    }

    /**
     * 通过id获取指定信息
     * @param id
     * @return
     * @throws Exception
     */
    public ItripHotelRoom getItripHotelRoomById(Long id)throws Exception{
        return itripHotelRoomMapper.getItripHotelRoomById(id);
    }

}
