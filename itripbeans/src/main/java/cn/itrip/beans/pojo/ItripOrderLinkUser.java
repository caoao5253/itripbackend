package cn.itrip.beans.pojo;

import java.util.Date;

public class ItripOrderLinkUser {
    /**
     * 主键
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderid;

    /**
     * 联系人id
     */
    private Long linkuserid;

    /**
     * 入住人姓名逗号分隔
     */
    private String linkusername;

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
     * 联系人id
     * @return linkUserId 联系人id
     */
    public Long getLinkuserid() {
        return linkuserid;
    }

    /**
     * 联系人id
     * @param linkuserid 联系人id
     */
    public void setLinkuserid(Long linkuserid) {
        this.linkuserid = linkuserid;
    }

    /**
     * 入住人姓名逗号分隔
     * @return linkUserName 入住人姓名逗号分隔
     */
    public String getLinkusername() {
        return linkusername;
    }

    /**
     * 入住人姓名逗号分隔
     * @param linkusername 入住人姓名逗号分隔
     */
    public void setLinkusername(String linkusername) {
        this.linkusername = linkusername == null ? null : linkusername.trim();
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