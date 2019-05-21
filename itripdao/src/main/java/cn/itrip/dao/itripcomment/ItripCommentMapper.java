package cn.itrip.dao.itripcomment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.comment.ItripListCommentVO;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripCommentMapper {

    /**
     * 根据酒店的id查询并计算所有点评的位置、设施、服务、卫生和综合评分(平均分)-add by donghai
     *
     * @param hotelId 酒店的id
     */
    public ItripScoreCommentVO getCommentAvgScore(@Param(value = "hotelId") Long hotelId) throws Exception;

    /**
     * 根据各条件查询酒店评论数量
     *
     * @param param
     * @return
     * @throws Exception
     */
    public Integer getItripCommentCountByMap(Map<String, Object> param) throws Exception;

    /**
     * 根据评论类型查看评论类容列表
     *
     * @param param
     * @return
     * @throws Exception
     */
    public List<ItripListCommentVO> getItripCommentListByMap(Map<String, Object> param) throws Exception;

    public Long insertItripComment(ItripComment itripComment) throws Exception;
}