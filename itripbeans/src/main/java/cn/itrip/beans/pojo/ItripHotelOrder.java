package cn.itrip.beans.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 */
public class ItripHotelOrder {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 订单类型(0:旅游产品 1:酒店产品 2：机票产品)
     */
    private Integer ordertype;

    /**
     * 订单号
     */
    private String orderno;

    /**
     * 交易编号
     */
    private String tradeno;

    /**
     * 冗余字段 酒店id
     */
    private Long hotelid;

    /**
     * 冗余字段 酒店名称
     */
    private String hotelname;

    /**
     * 房间id
     */
    private Long roomid;

    /**
     * 消耗数量
     */
    private Integer count;

    /**
     * 预订天数
     */
    private Integer bookingdays;

    /**
     * 入住时间
     */
    private Date checkindate;

    /**
     * 退房时间
     */
    private Date checkoutdate;

    /**
     * 订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
     */
    private Integer orderstatus;

    /**
     * 支付金额
     */
    private BigDecimal payamount;

    /**
     * 支付方式:1:支付宝 2:微信 3:到店付
     */
    private Integer paytype;

    /**
     * 联系手机号
     */
    private String noticephone;

    /**
     * 联系邮箱
     */
    private String noticeemail;

    /**
     * 是否需要发票（0：不需要 1：需要）
     */
    private Integer isneedinvoice;

    /**
     * 发票类型（0：个人 1：公司）
     */
    private Integer invoicetype;

    /**
     * 发票抬头
     */
    private String invoicehead;

    /**
     * 入住人名称
     */
    private String linkusername;

    /**
     * 0:WEB端 1:手机端 2:其他客户端
     */
    private Integer booktype;

    /**
     * 预定时间
     */
    private Date creationdate;

    /**
     * 
     */
    private Long createdby;

    /**
     * 支付完成时间
     */
    private Date modifydate;

    /**
     * 
     */
    private Long modifiedby;

    /**
     * 特殊需求
     */
    private String specialrequirement;

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
     * 用户id
     * @return userId 用户id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 用户id
     * @param userid 用户id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 订单类型(0:旅游产品 1:酒店产品 2：机票产品)
     * @return orderType 订单类型(0:旅游产品 1:酒店产品 2：机票产品)
     */
    public Integer getOrdertype() {
        return ordertype;
    }

    /**
     * 订单类型(0:旅游产品 1:酒店产品 2：机票产品)
     * @param ordertype 订单类型(0:旅游产品 1:酒店产品 2：机票产品)
     */
    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    /**
     * 订单号
     * @return orderNo 订单号
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * 订单号
     * @param orderno 订单号
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    /**
     * 交易编号
     * @return tradeNo 交易编号
     */
    public String getTradeno() {
        return tradeno;
    }

    /**
     * 交易编号
     * @param tradeno 交易编号
     */
    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
    }

    /**
     * 冗余字段 酒店id
     * @return hotelId 冗余字段 酒店id
     */
    public Long getHotelid() {
        return hotelid;
    }

    /**
     * 冗余字段 酒店id
     * @param hotelid 冗余字段 酒店id
     */
    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    /**
     * 冗余字段 酒店名称
     * @return hotelName 冗余字段 酒店名称
     */
    public String getHotelname() {
        return hotelname;
    }

    /**
     * 冗余字段 酒店名称
     * @param hotelname 冗余字段 酒店名称
     */
    public void setHotelname(String hotelname) {
        this.hotelname = hotelname == null ? null : hotelname.trim();
    }

    /**
     * 房间id
     * @return roomId 房间id
     */
    public Long getRoomid() {
        return roomid;
    }

    /**
     * 房间id
     * @param roomid 房间id
     */
    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    /**
     * 消耗数量
     * @return count 消耗数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 消耗数量
     * @param count 消耗数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 预订天数
     * @return bookingDays 预订天数
     */
    public Integer getBookingdays() {
        return bookingdays;
    }

    /**
     * 预订天数
     * @param bookingdays 预订天数
     */
    public void setBookingdays(Integer bookingdays) {
        this.bookingdays = bookingdays;
    }

    /**
     * 入住时间
     * @return checkInDate 入住时间
     */
    public Date getCheckindate() {
        return checkindate;
    }

    /**
     * 入住时间
     * @param checkindate 入住时间
     */
    public void setCheckindate(Date checkindate) {
        this.checkindate = checkindate;
    }

    /**
     * 退房时间
     * @return checkOutDate 退房时间
     */
    public Date getCheckoutdate() {
        return checkoutdate;
    }

    /**
     * 退房时间
     * @param checkoutdate 退房时间
     */
    public void setCheckoutdate(Date checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    /**
     * 订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
     * @return orderStatus 订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
     */
    public Integer getOrderstatus() {
        return orderstatus;
    }

    /**
     * 订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
     * @param orderstatus 订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
     */
    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    /**
     * 支付金额
     * @return payAmount 支付金额
     */
    public BigDecimal getPayamount() {
        return payamount;
    }

    /**
     * 支付金额
     * @param payamount 支付金额
     */
    public void setPayamount(BigDecimal payamount) {
        this.payamount = payamount;
    }

    /**
     * 支付方式:1:支付宝 2:微信 3:到店付
     * @return payType 支付方式:1:支付宝 2:微信 3:到店付
     */
    public Integer getPaytype() {
        return paytype;
    }

    /**
     * 支付方式:1:支付宝 2:微信 3:到店付
     * @param paytype 支付方式:1:支付宝 2:微信 3:到店付
     */
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    /**
     * 联系手机号
     * @return noticePhone 联系手机号
     */
    public String getNoticephone() {
        return noticephone;
    }

    /**
     * 联系手机号
     * @param noticephone 联系手机号
     */
    public void setNoticephone(String noticephone) {
        this.noticephone = noticephone == null ? null : noticephone.trim();
    }

    /**
     * 联系邮箱
     * @return noticeEmail 联系邮箱
     */
    public String getNoticeemail() {
        return noticeemail;
    }

    /**
     * 联系邮箱
     * @param noticeemail 联系邮箱
     */
    public void setNoticeemail(String noticeemail) {
        this.noticeemail = noticeemail == null ? null : noticeemail.trim();
    }

    /**
     * 是否需要发票（0：不需要 1：需要）
     * @return isNeedInvoice 是否需要发票（0：不需要 1：需要）
     */
    public Integer getIsneedinvoice() {
        return isneedinvoice;
    }

    /**
     * 是否需要发票（0：不需要 1：需要）
     * @param isneedinvoice 是否需要发票（0：不需要 1：需要）
     */
    public void setIsneedinvoice(Integer isneedinvoice) {
        this.isneedinvoice = isneedinvoice;
    }

    /**
     * 发票类型（0：个人 1：公司）
     * @return invoiceType 发票类型（0：个人 1：公司）
     */
    public Integer getInvoicetype() {
        return invoicetype;
    }

    /**
     * 发票类型（0：个人 1：公司）
     * @param invoicetype 发票类型（0：个人 1：公司）
     */
    public void setInvoicetype(Integer invoicetype) {
        this.invoicetype = invoicetype;
    }

    /**
     * 发票抬头
     * @return invoiceHead 发票抬头
     */
    public String getInvoicehead() {
        return invoicehead;
    }

    /**
     * 发票抬头
     * @param invoicehead 发票抬头
     */
    public void setInvoicehead(String invoicehead) {
        this.invoicehead = invoicehead == null ? null : invoicehead.trim();
    }

    /**
     * 入住人名称
     * @return linkUserName 入住人名称
     */
    public String getLinkusername() {
        return linkusername;
    }

    /**
     * 入住人名称
     * @param linkusername 入住人名称
     */
    public void setLinkusername(String linkusername) {
        this.linkusername = linkusername == null ? null : linkusername.trim();
    }

    /**
     * 0:WEB端 1:手机端 2:其他客户端
     * @return bookType 0:WEB端 1:手机端 2:其他客户端
     */
    public Integer getBooktype() {
        return booktype;
    }

    /**
     * 0:WEB端 1:手机端 2:其他客户端
     * @param booktype 0:WEB端 1:手机端 2:其他客户端
     */
    public void setBooktype(Integer booktype) {
        this.booktype = booktype;
    }

    /**
     * 预定时间
     * @return creationDate 预定时间
     */
    public Date getCreationdate() {
        return creationdate;
    }

    /**
     * 预定时间
     * @param creationdate 预定时间
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
     * 支付完成时间
     * @return modifyDate 支付完成时间
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * 支付完成时间
     * @param modifydate 支付完成时间
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

    /**
     * 特殊需求
     * @return specialRequirement 特殊需求
     */
    public String getSpecialrequirement() {
        return specialrequirement;
    }

    /**
     * 特殊需求
     * @param specialrequirement 特殊需求
     */
    public void setSpecialrequirement(String specialrequirement) {
        this.specialrequirement = specialrequirement == null ? null : specialrequirement.trim();
    }
}