package cn.itrip.service.labeldic;

import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripLabelDicVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripLabelDicService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ItripLabelDic record);

    ItripLabelDic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItripLabelDic record);


    /**
     * 根据各种条件查询对应得数据
     * @param param
     * @return
     * @throws Exception
     */
    public List<ItripLabelDic> getItripLabelDicListByMap(Map<String, Object> param)throws Exception;

    /**
     * 根据parentId查询数据字典(本次查询的是房间类型)
     * @param parentId
     */
    public List<ItripLabelDicVO> getItripLabelDicByParentId(@Param(value = "parentId") Long parentId)throws Exception;

}
