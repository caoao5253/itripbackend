package cn.itrip.service.image;

import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripImageVO;

import java.util.List;
import java.util.Map;

public interface ItripImageService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ItripImage record);

    ItripImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItripImage record);

    /**
     * 查询相关图片信息
     * @param param
     * @return
     * @throws Exception
     */
    public List<ItripImageVO> getItripImageListByMap(Map<String,Object> param)throws Exception;
}
