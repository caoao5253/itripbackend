package cn.itrip.service.areadic;

import cn.itrip.beans.pojo.ItripAreaDic;

import java.util.List;
import java.util.Map;

public interface ItripAreaDicService {
    /**
     * 通过主键查询指定数据信息
     * @param id
     * @return
     */
    ItripAreaDic selectByPrimaryKey(Long id) throws  Exception;
    /**
     * 根据主键删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id) throws  Exception;

    /**
     * 判断对象的值是否存在,然后作为参数入参
     * @param record
     * @return
     */
    int insertSelective(ItripAreaDic record) throws  Exception;

    /**
     * 判断对象中得属性值是否存在，存在则作为set更新值
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ItripAreaDic record) throws  Exception;

    public List<ItripAreaDic> getItripAreaDicListByMap(Map<String,Object> param);
}
