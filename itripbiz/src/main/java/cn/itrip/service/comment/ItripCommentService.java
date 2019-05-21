package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.comment.ItripListCommentVO;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.common.Page;

import java.util.List;
import java.util.Map;

public interface ItripCommentService {


    /**
     * 根据酒店的id查询并计算所有点评的位置、设施、服务、卫生和综合评分(平均分)-add by donghai
     * @param hotelId 酒店的id
     */
    public ItripScoreCommentVO getAvgAndTotalScore(Long hotelId) throws Exception;

    /**
     * 根据各条件查询酒店评论
     * @param param
     * @return
     * @throws Exception
     */
    public Integer getItripCommentCountByMap(Map<String, Object> param)throws Exception;

    /**
     * 根据各种评论类型查询评论内容列表并分页
     * @param param      各种评论类型
     * @param pageNo     页数 第几页
     * @param pageSize   每一页多少行显示数据
     * @return
     * @throws Exception
     */
    public Page<ItripListCommentVO> queryItripCommentPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;



    /**
     *  新增评论
     *  根据传参条件判断插入多少条数据
     * @param obj          评论表对象
     * @param itripImages  图片表集合
     * @return
     * @throws Exception
     */
    public boolean itriptxAddItripComment(ItripComment obj, List<ItripImage> itripImages)throws Exception;
}
