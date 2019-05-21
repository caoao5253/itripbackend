package cn.itrip.service.labeldic;

import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.dao.labeldic.ItripLabelDicMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("itripLabelDicService")
public class ItripLabelDicServiceImpl implements  ItripLabelDicService{
    @Resource(name = "itripLabelDicMapper")
    private ItripLabelDicMapper itripLabelDicMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return itripLabelDicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(ItripLabelDic record) {
        return itripLabelDicMapper.insertSelective(record);
    }

    @Override
    public ItripLabelDic selectByPrimaryKey(Long id) {
        return itripLabelDicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ItripLabelDic record) {
        return itripLabelDicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ItripLabelDic> getItripLabelDicListByMap(Map<String, Object> param) throws Exception {
        return itripLabelDicMapper.getItripLabelDicListByMap(param);
    }

    /**
     * 根据parentId查询数据字典(本次查询的是房间类型)
     * @param parentId
     */
    @Override
    public List<ItripLabelDicVO> getItripLabelDicByParentId(Long parentId) throws Exception {
        return itripLabelDicMapper.getItripLabelDicByParentId(parentId);
    }
}
