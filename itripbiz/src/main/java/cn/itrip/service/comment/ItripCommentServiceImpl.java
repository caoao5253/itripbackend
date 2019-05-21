package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.comment.ItripListCommentVO;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.common.*;
import cn.itrip.dao.hotelorder.ItripHotelOrderMapper;
import cn.itrip.dao.itripcomment.ItripCommentMapper;
import cn.itrip.dao.itripimage.ItripImageMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service("itripCommentService")
public class ItripCommentServiceImpl implements ItripCommentService {
    @Resource(name = "itripCommentMapper")
    private ItripCommentMapper itripCommentMapper;
    @Resource
    private ItripImageMapper itripImageMapper;
    @Resource
    private ItripHotelOrderMapper itripHotelOrderMapper;
    private Logger logger = Logger.getLogger(ItripCommentServiceImpl.class);

    @Override
    /**
     * 根据酒店的id查询并计算所有点评的位置、设施、服务、卫生和综合评分(平均分)-add by donghai
     * @param hotelId 酒店的id
     */
    public ItripScoreCommentVO getAvgAndTotalScore(Long hotelId) throws Exception {
        return itripCommentMapper.getCommentAvgScore(hotelId);
    }

    @Override
    /**
     * 根据各条件查询酒店评论的数量
     * @param param
     * @return
     * @throws Exception
     */
    public Integer getItripCommentCountByMap(Map<String, Object> param) throws Exception {
        return itripCommentMapper.getItripCommentCountByMap(param);
    }

    @Override
    /**
     * 根据各种评论类型查询评论内容列表并分页
     * @param param      各种评论类型
     * @param pageNo     页数 第几页
     * @param pageSize   每一页多少行显示数据
     * @return
     * @throws Exception
     */
    public Page<ItripListCommentVO> queryItripCommentPageByMap(Map<String,Object> param, Integer pageNo, Integer pageSize)throws Exception{
        Integer total = itripCommentMapper.getItripCommentCountByMap(param);//通过评论类型查询数据总数量
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;//位置偏移量
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;//每行显示数量
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());//limit位置偏移量
        param.put("pageSize", page.getPageSize());//查询数量
        //根据各种评论类型查询评论内容列表（且分页）
        List<ItripListCommentVO> itripCommentList = itripCommentMapper.getItripCommentListByMap(param);
        page.setRows(itripCommentList);
        return page;
    }


    /**
     * 新增评论信息
     * @param obj          评论表对象
     * @param itripImages  图片表集合
     * @return
     * @throws Exception
     */
    public boolean itriptxAddItripComment(ItripComment obj, List<ItripImage> itripImages)throws Exception{
        if(null != obj ){
            //计算综合评分，综合评分=(设施+卫生+位置+服务)/4
            float score = 0;
            //计算总分
            int sum = obj.getFacilitiesscore()+obj.getHygienescore()+obj.getPositionscore()+obj.getServicescore();
            //计算综合评分
            score = BigDecimalUtil.OperationASMD(sum,4, BigDecimalUtil.BigDecimalOprations.divide,1, BigDecimal.ROUND_DOWN).floatValue();
            //对结果四舍五入
            obj.setScore(Math.round(score));
            Long commentId = 0L;//评论id
            if(itripCommentMapper.insertItripComment(obj) > 0 ){//新增评论成功
                commentId = obj.getId();//新增后的评论id
                logger.debug("新增评论id：================ " + commentId);
                //判断图片集合对象是否存在且是否新增评论成功
                if(null != itripImages && itripImages.size() > 0 && commentId > 0){
                    for (ItripImage itripImage:itripImages) {
                        itripImage.setTargetid(commentId);//评论id
                        itripImageMapper.insertItripImage(itripImage);
                    }
                }
                //更新订单表-订单状态为4（已评论）
                itripHotelOrderMapper.updateHotelOrderStatus(obj.getOrderid(),obj.getCreatedby());
                return true;
            }
        }
        return false;
    }

}
