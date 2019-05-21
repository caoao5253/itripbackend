package cn.itrip.beans.pojo;

import java.util.Date;

public class ItripComment {
    /**
     * 主键
     */
    private Long id;

    /**
     * 如果产品是酒店的话 改字段保存酒店id
     */
    private Long hotelid;

    /**
     * 商品id
     */
    private Long productid;

    /**
     * 订单id
     */
    private Long orderid;

    /**
     * 商品类型(0:旅游产品 1:酒店产品 2:机票产品)
     */
    private Integer producttype;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 用户编号
     */
    private Long userid;

    /**
     * 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片
     */
    private Integer ishavingimg;

    /**
     * 位置评分
     */
    private Integer positionscore;

    /**
     * 设施评分
     */
    private Integer facilitiesscore;

    /**
     * 服务评分
     */
    private Integer servicescore;

    /**
     * 卫生评分
     */
    private Integer hygienescore;

    /**
     * 综合评分
     */
    private Integer score;

    /**
     * 出游类型
     */
    private Long tripmode;

    /**
     * 是否满意（0：有待改善 1：值得推荐）
     */
    private Integer isok;

    /**
     * 超创建时间
     */
    private Date creationdate;

    /**
     * 创建id 对应用户id
     */
    private Long createdby;

    /**
     * 更新时间
     */
    private Date modifydate;

    /**
     * 更新者
     */
    private Long modifiedby;

    /**
     * 主键
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 如果产品是酒店的话 改字段保存酒店id
     * @return hotelId 如果产品是酒店的话 改字段保存酒店id
     */
    public Long getHotelid() {
        return hotelid;
    }

    /**
     * 如果产品是酒店的话 改字段保存酒店id
     * @param hotelid 如果产品是酒店的话 改字段保存酒店id
     */
    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    /**
     * 商品id
     * @return productId 商品id
     */
    public Long getProductid() {
        return productid;
    }

    /**
     * 商品id
     * @param productid 商品id
     */
    public void setProductid(Long productid) {
        this.productid = productid;
    }

    /**
     * 订单id
     * @return orderId 订单id
     */
    public Long getOrderid() {
        return orderid;
    }

    /**
     * 订单id
     * @param orderid 订单id
     */
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    /**
     * 商品类型(0:旅游产品 1:酒店产品 2:机票产品)
     * @return productType 商品类型(0:旅游产品 1:酒店产品 2:机票产品)
     */
    public Integer getProducttype() {
        return producttype;
    }

    /**
     * 商品类型(0:旅游产品 1:酒店产品 2:机票产品)
     * @param producttype 商品类型(0:旅游产品 1:酒店产品 2:机票产品)
     */
    public void setProducttype(Integer producttype) {
        this.producttype = producttype;
    }

    /**
     * 评论内容
     * @return content 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评论内容
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 用户编号
     * @return userId 用户编号
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 用户编号
     * @param userid 用户编号
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片
     * @return isHavingImg 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片
     */
    public Integer getIshavingimg() {
        return ishavingimg;
    }

    /**
     * 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片
     * @param ishavingimg 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片
     */
    public void setIshavingimg(Integer ishavingimg) {
        this.ishavingimg = ishavingimg;
    }

    /**
     * 位置评分
     * @return positionScore 位置评分
     */
    public Integer getPositionscore() {
        return positionscore;
    }

    /**
     * 位置评分
     * @param positionscore 位置评分
     */
    public void setPositionscore(Integer positionscore) {
        this.positionscore = positionscore;
    }

    /**
     * 设施评分
     * @return facilitiesScore 设施评分
     */
    public Integer getFacilitiesscore() {
        return facilitiesscore;
    }

    /**
     * 设施评分
     * @param facilitiesscore 设施评分
     */
    public void setFacilitiesscore(Integer facilitiesscore) {
        this.facilitiesscore = facilitiesscore;
    }

    /**
     * 服务评分
     * @return serviceScore 服务评分
     */
    public Integer getServicescore() {
        return servicescore;
    }

    /**
     * 服务评分
     * @param servicescore 服务评分
     */
    public void setServicescore(Integer servicescore) {
        this.servicescore = servicescore;
    }

    /**
     * 卫生评分
     * @return hygieneScore 卫生评分
     */
    public Integer getHygienescore() {
        return hygienescore;
    }

    /**
     * 卫生评分
     * @param hygienescore 卫生评分
     */
    public void setHygienescore(Integer hygienescore) {
        this.hygienescore = hygienescore;
    }

    /**
     * 综合评分
     * @return score 综合评分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 综合评分
     * @param score 综合评分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 出游类型
     * @return tripMode 出游类型
     */
    public Long getTripmode() {
        return tripmode;
    }

    /**
     * 出游类型
     * @param tripmode 出游类型
     */
    public void setTripmode(Long tripmode) {
        this.tripmode = tripmode;
    }

    /**
     * 是否满意（0：有待改善 1：值得推荐）
     * @return isOk 是否满意（0：有待改善 1：值得推荐）
     */
    public Integer getIsok() {
        return isok;
    }

    /**
     * 是否满意（0：有待改善 1：值得推荐）
     * @param isok 是否满意（0：有待改善 1：值得推荐）
     */
    public void setIsok(Integer isok) {
        this.isok = isok;
    }

    /**
     * 
     * @return creationDate 
     */
    public Date getCreationdate() {
        return creationdate;
    }

    /**
     * 
     * @param creationdate 
     */
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    /**
     * 
     * @return createdBy 
     */
    public Long getCreatedby() {
        return createdby;
    }

    /**
     * 
     * @param createdby 
     */
    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    /**
     * 
     * @return modifyDate 
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * 
     * @param modifydate 
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    /**
     * 
     * @return modifiedBy 
     */
    public Long getModifiedby() {
        return modifiedby;
    }

    /**
     * 
     * @param modifiedby 
     */
    public void setModifiedby(Long modifiedby) {
        this.modifiedby = modifiedby;
    }
}