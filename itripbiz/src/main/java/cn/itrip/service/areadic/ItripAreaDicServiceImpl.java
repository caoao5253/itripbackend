package cn.itrip.service.areadic;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.dao.areadic.ItripAreaDicMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("itripAreaDicService")
public class ItripAreaDicServiceImpl implements  ItripAreaDicService {
    @Resource(name = "itripAreaDicMapper")
    private ItripAreaDicMapper itripAreaDicMapper;
    @Override
    public ItripAreaDic selectByPrimaryKey(Long id)throws  Exception {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Long id)throws  Exception {
        return 0;
    }

    @Override
    public int insertSelective(ItripAreaDic record)throws  Exception {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(ItripAreaDic record)throws  Exception {
        return 0;
    }

    @Override
    public List<ItripAreaDic> getItripAreaDicListByMap(Map<String, Object> param) {
        return itripAreaDicMapper.getItripAreaDicListByMap(param);
    }
}
