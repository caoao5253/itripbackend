package cn.itrip.dao.itripimage;

import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripImageVO;

import java.util.List;
import java.util.Map;

public interface ItripImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItripImage record);

    int insertSelective(ItripImage record);

    ItripImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItripImage record);

    int updateByPrimaryKey(ItripImage record);

    public Integer insertItripImage(ItripImage itripImage)throws Exception;

    /**
     * 根据条件查询对应得图片
     * @param param
     * @return
     * @throws Exception
     */
    public List<ItripImageVO> getItripImageListByMap(Map<String, Object> param)throws Exception;
}