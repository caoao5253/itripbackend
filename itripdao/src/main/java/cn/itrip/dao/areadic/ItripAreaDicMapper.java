package cn.itrip.dao.areadic;

import cn.itrip.beans.pojo.ItripAreaDic;

import java.util.List;
import java.util.Map;

public interface ItripAreaDicMapper {
    /**
     * @param id
     * @return
     * @通过主键查询指定数据信息
     */
    ItripAreaDic selectByPrimaryKey(Long id) throws Exception;

    /**
     * @param id
     * @return
     * @根据主键删除数据
     */
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
     * @param record
     * @return
     * @判断对象的值是否存在,然后作为参数入参
     */
    int insertSelective(ItripAreaDic record) throws Exception;

    /**
     * @param record
     * @return
     * @判断对象中得属性值是否存在，存在则作为set更新值
     */
    int updateByPrimaryKeySelective(ItripAreaDic record) throws Exception;

    public List<ItripAreaDic> getItripAreaDicListByMap(Map<String,Object> param);
}