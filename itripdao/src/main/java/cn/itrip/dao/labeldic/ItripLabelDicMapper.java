package cn.itrip.dao.labeldic;

import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripLabelDicVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripLabelDicMapper {
    int deleteByPrimaryKey(Long id);


    int insertSelective(ItripLabelDic record);

    ItripLabelDic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItripLabelDic record);

    public List<ItripLabelDic> getItripLabelDicListByMap(Map<String, Object> param)throws Exception;
    /**
     * 根据parentId查询数据字典
     * @param parentId
     */
    public List<ItripLabelDicVO> getItripLabelDicByParentId(@Param(value = "parentId") Long parentId)throws Exception;
}