package cn.itrip.service.image;

import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.dao.itripimage.ItripImageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("itripImageService")
public class ItripImageServiceImpl  implements ItripImageService{
    @Resource
    private ItripImageMapper itripImageMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(ItripImage record) {
        return 0;
    }

    @Override
    public ItripImage selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ItripImage record) {
        return 0;
    }

    @Override
    //<!--通过相关条件 查询图片信息-->
    public List<ItripImageVO> getItripImageListByMap(Map<String, Object> param) throws Exception {
        return itripImageMapper.getItripImageListByMap(param);

    }
}
