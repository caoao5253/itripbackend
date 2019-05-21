package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripAreaDic;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.hotel.HotelVideoDescVO;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import cn.itrip.dao.hotel.ItripHotelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("itripHotelService")
public class ItripHotelServiceImpl implements ItripHotelService {
    @Resource(name="itripHotelMapper")
    private ItripHotelMapper itripHotelMapper;

    @Override
    public ItripHotel getItripHotelById(Long id) throws Exception {
        return itripHotelMapper.getItripHotelById(id);
    }

    @Override
    public List<ItripLabelDic> getHotelFeatureByHotelId(Long id) throws Exception {
        return itripHotelMapper.getHotelFeatureByHotelId(id);
    }

    @Override
    public List<ItripAreaDic> getHotelAreaByHotelId(Long id) throws Exception {
        return itripHotelMapper.getHotelAreaByHotelId(id);
    }

    @Override
    public HotelVideoDescVO getVideoDescByHotelId(Long id) throws Exception {
        //酒店详情类
        HotelVideoDescVO hotelVideoDescVO = new HotelVideoDescVO();

        //获取酒店所存在的商圈地段数据
        List<ItripAreaDic> itripAreaDicList = itripHotelMapper.getHotelAreaByHotelId(id);
        List<String> tempList1 = new ArrayList<>();
        for (ItripAreaDic itripAreaDic:itripAreaDicList) {
            tempList1.add(itripAreaDic.getName());
        }
        hotelVideoDescVO.setTradingAreaNameList(tempList1);

        //获取指定酒店的特色及特色描述
        List<ItripLabelDic> itripLabelDicList = itripHotelMapper.getHotelFeatureByHotelId(id);
        List<String> tempList2 = new ArrayList<>();
        for (ItripLabelDic itripLabelDic:itripLabelDicList) {
            tempList2.add(itripLabelDic.getName());
        }
        hotelVideoDescVO.setHotelFeatureList(tempList2);

        //酒店名称
        hotelVideoDescVO.setHotelName(itripHotelMapper.getItripHotelById(id).getHotelname());
        return hotelVideoDescVO;
    }

    @Override//根据酒店id查找酒店设施
    public ItripSearchFacilitiesHotelVO getHotelFacilitiesById(Long id) throws Exception {
        return itripHotelMapper.getHotelFacilitiesById(id);
    }

    @Override//根据酒店id查找酒店政策
    public ItripSearchPolicyHotelVO getHotelhotelPolicyById(Long id) throws Exception {
        return itripHotelMapper.getHotelhotelPolicyById(id);
    }

    @Override//通过酒店id查询其特色 和介绍
    public List<ItripSearchDetailsHotelVO> queryHotelDetailsByhotelId(Long hotelId) throws Exception {
        List<ItripLabelDic> itripLabelDicList = new ArrayList<>();
        //酒店特色和介绍类
        ItripSearchDetailsHotelVO vo = new ItripSearchDetailsHotelVO();
        List<ItripSearchDetailsHotelVO> list = new ArrayList<ItripSearchDetailsHotelVO>();
        vo.setName("酒店介绍");
        //酒店id查询酒店表中的数据然后获取酒店介绍信息
        vo.setDescription(itripHotelMapper.getItripHotelById(hotelId).getDetails());
        //通过酒店id查询酒店特色及特色而描述
        itripLabelDicList = itripHotelMapper.getHotelFeatureByHotelId(hotelId);
        list.add(vo);
        for (ItripLabelDic itripLabelDic:itripLabelDicList) {
            ItripSearchDetailsHotelVO vo2 = new ItripSearchDetailsHotelVO();
            vo2.setName(itripLabelDic.getName());//将酒店特色昵称注入进去
            vo2.setDescription(itripLabelDic.getDescription());//然后注入其描述
            list.add(vo2);
        }
        return list;

    }
}
