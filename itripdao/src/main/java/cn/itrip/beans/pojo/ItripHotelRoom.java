package cn.itrip.beans.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ItripHotelRoom {
    /**
     * 主键
     */
    private Long id;

    /**
     * 酒店ID
     */
    private Long hotelid;

    /**
     * 房间名称
     */
    private String roomtitle;

    /**
     * 房间价格
     */
    private BigDecimal roomprice;

    /**
     * 酒店床型
     */
    private Long roombedtypeid;

    /**
     * 是否包含早餐
     */
    private Integer ishavingbreakfast;

    /**
     * 1:在线付 2:到店付 3:不限
     */
    private Integer paytype;

    /**
     * 满意度（冗余字段，在用户评论后更新）
     */
    private Long satisfaction;

    /**
     * 是否可预订(0:不可以 1:可以)
     */
    private Integer isbook;

    /**
     * 是否可取消(0:不可 1:可以)
     */
    private Integer iscancel;

    /**
     * 是否及时响应(0:不及时 1:及时)
     */
    private Integer istimelyresponse;

    /**
     * 
     */
    private Date creationdate;

    /**
     * 
     */
    private Long createdby;

    /**
     * 
     */
    private Date modifydate;

    /**
     * 
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
     * 酒店ID
     * @return hotelId 酒店ID
     */
    public Long getHotelid() {
        return hotelid;
    }

    /**
     * 酒店ID
     * @param hotelid 酒店ID
     */
    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    /**
     * 房间名称
     * @return roomTitle 房间名称
     */
    public String getRoomtitle() {
        return roomtitle;
    }

    /**
     * 房间名称
     * @param roomtitle 房间名称
     */
    public void setRoomtitle(String roomtitle) {
        this.roomtitle = roomtitle == null ? null : roomtitle.trim();
    }

    /**
     * 房间价格
     * @return roomPrice 房间价格
     */
    public BigDecimal getRoomprice() {
        return roomprice;
    }

    /**
     * 房间价格
     * @param roomprice 房间价格
     */
    public void setRoomprice(BigDecimal roomprice) {
        this.roomprice = roomprice;
    }

    /**
     * 酒店床型
     * @return roomBedTypeId 酒店床型
     */
    public Long getRoombedtypeid() {
        return roombedtypeid;
    }

    /**
     * 酒店床型
     * @param roombedtypeid 酒店床型
     */
    public void setRoombedtypeid(Long roombedtypeid) {
        this.roombedtypeid = roombedtypeid;
    }

    /**
     * 是否包含早餐
     * @return isHavingBreakfast 是否包含早餐
     */
    public Integer getIshavingbreakfast() {
        return ishavingbreakfast;
    }

    /**
     * 是否包含早餐
     * @param ishavingbreakfast 是否包含早餐
     */
    public void setIshavingbreakfast(Integer ishavingbreakfast) {
        this.ishavingbreakfast = ishavingbreakfast;
    }

    /**
     * 1:在线付 2:到店付 3:不限
     * @return payType 1:在线付 2:到店付 3:不限
     */
    public Integer getPaytype() {
        return paytype;
    }

    /**
     * 1:在线付 2:到店付 3:不限
     * @param paytype 1:在线付 2:到店付 3:不限
     */
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    /**
     * 满意度（冗余字段，在用户评论后更新）
     * @return satisfaction 满意度（冗余字段，在用户评论后更新）
     */
    public Long getSatisfaction() {
        return satisfaction;
    }

    /**
     * 满意度（冗余字段，在用户评论后更新）
     * @param satisfaction 满意度（冗余字段，在用户评论后更新）
     */
    public void setSatisfaction(Long satisfaction) {
        this.satisfaction = satisfaction;
    }

    /**
     * 是否可预订(0:不可以 1:可以)
     * @return isBook 是否可预订(0:不可以 1:可以)
     */
    public Integer getIsbook() {
        return isbook;
    }

    /**
     * 是否可预订(0:不可以 1:可以)
     * @param isbook 是否可预订(0:不可以 1:可以)
     */
    public void setIsbook(Integer isbook) {
        this.isbook = isbook;
    }

    /**
     * 是否可取消(0:不可 1:可以)
     * @return isCancel 是否可取消(0:不可 1:可以)
     */
    public Integer getIscancel() {
        return iscancel;
    }

    /**
     * 是否可取消(0:不可 1:可以)
     * @param iscancel 是否可取消(0:不可 1:可以)
     */
    public void setIscancel(Integer iscancel) {
        this.iscancel = iscancel;
    }

    /**
     * 是否及时响应(0:不及时 1:及时)
     * @return isTimelyResponse 是否及时响应(0:不及时 1:及时)
     */
    public Integer getIstimelyresponse() {
        return istimelyresponse;
    }

    /**
     * 是否及时响应(0:不及时 1:及时)
     * @param istimelyresponse 是否及时响应(0:不及时 1:及时)
     */
    public void setIstimelyresponse(Integer istimelyresponse) {
        this.istimelyresponse = istimelyresponse;
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