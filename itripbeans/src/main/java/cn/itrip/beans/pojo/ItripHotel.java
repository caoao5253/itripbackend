package cn.itrip.beans.pojo;

import java.util.Date;

/**
 * 酒店表对象
 */
public class ItripHotel {
    /**
     * 主键
     */
    private Long id;

    /**
     * 酒店名称
     */
    private String hotelname;

    /**
     * 所在国家id
     */
    private Long countryid;

    /**
     * 所在省份id
     */
    private Long provinceid;

    /**
     * 所在城市id
     */
    private Long cityid;

    /**
     * 酒店所在地址
     */
    private String address;

    /**
     * 酒店介绍（保存附文本）
     */
    private String details;

    /**
     * 酒店设施
     */
    private String facilities;

    /**
     * 酒店政策
     */
    private String hotelpolicy;

    /**
     * 酒店类型(1:国内酒店 2:国际酒店)
     */
    private Integer hoteltype;

    /**
     * (1:经济酒店  2:二星级酒店  3:三星级 4:四星酒店 5星酒店)
     */
    private Integer hotellevel;

    /**
     * 是否是团购酒店
     */
    private Integer isgrouppurchase;

    /**
     * 城市名称 冗余字段
     */
    private String redundantcityname;

    /**
     * 省名称 冗余字段
     */
    private String redundantprovincename;

    /**
     * 国家名称 冗余字段
     */
    private String redundantcountryname;

    /**
     * 酒店库存（冗余，每天开定时任务的时候更新）
     */
    private Integer redundanthotelstore;

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
     * 酒店名称
     * @return hotelName 酒店名称
     */
    public String getHotelname() {
        return hotelname;
    }

    /**
     * 酒店名称
     * @param hotelname 酒店名称
     */
    public void setHotelname(String hotelname) {
        this.hotelname = hotelname == null ? null : hotelname.trim();
    }

    /**
     * 所在国家id
     * @return countryId 所在国家id
     */
    public Long getCountryid() {
        return countryid;
    }

    /**
     * 所在国家id
     * @param countryid 所在国家id
     */
    public void setCountryid(Long countryid) {
        this.countryid = countryid;
    }

    /**
     * 所在省份id
     * @return provinceId 所在省份id
     */
    public Long getProvinceid() {
        return provinceid;
    }

    /**
     * 所在省份id
     * @param provinceid 所在省份id
     */
    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    /**
     * 所在城市id
     * @return cityId 所在城市id
     */
    public Long getCityid() {
        return cityid;
    }

    /**
     * 所在城市id
     * @param cityid 所在城市id
     */
    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }

    /**
     * 酒店所在地址
     * @return address 酒店所在地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 酒店所在地址
     * @param address 酒店所在地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 酒店介绍（保存附文本）
     * @return details 酒店介绍（保存附文本）
     */
    public String getDetails() {
        return details;
    }

    /**
     * 酒店介绍（保存附文本）
     * @param details 酒店介绍（保存附文本）
     */
    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    /**
     * 酒店设施
     * @return facilities 酒店设施
     */
    public String getFacilities() {
        return facilities;
    }

    /**
     * 酒店设施
     * @param facilities 酒店设施
     */
    public void setFacilities(String facilities) {
        this.facilities = facilities == null ? null : facilities.trim();
    }

    /**
     * 酒店政策
     * @return hotelPolicy 酒店政策
     */
    public String getHotelpolicy() {
        return hotelpolicy;
    }

    /**
     * 酒店政策
     * @param hotelpolicy 酒店政策
     */
    public void setHotelpolicy(String hotelpolicy) {
        this.hotelpolicy = hotelpolicy == null ? null : hotelpolicy.trim();
    }

    /**
     * 酒店类型(1:国内酒店 2:国际酒店)
     * @return hotelType 酒店类型(1:国内酒店 2:国际酒店)
     */
    public Integer getHoteltype() {
        return hoteltype;
    }

    /**
     * 酒店类型(1:国内酒店 2:国际酒店)
     * @param hoteltype 酒店类型(1:国内酒店 2:国际酒店)
     */
    public void setHoteltype(Integer hoteltype) {
        this.hoteltype = hoteltype;
    }

    /**
     * (1:经济酒店  2:二星级酒店  3:三星级 4:四星酒店 5星酒店)
     * @return hotelLevel (1:经济酒店  2:二星级酒店  3:三星级 4:四星酒店 5星酒店)
     */
    public Integer getHotellevel() {
        return hotellevel;
    }

    /**
     * (1:经济酒店  2:二星级酒店  3:三星级 4:四星酒店 5星酒店)
     * @param hotellevel (1:经济酒店  2:二星级酒店  3:三星级 4:四星酒店 5星酒店)
     */
    public void setHotellevel(Integer hotellevel) {
        this.hotellevel = hotellevel;
    }

    /**
     * 是否是团购酒店
     * @return isGroupPurchase 是否是团购酒店
     */
    public Integer getIsgrouppurchase() {
        return isgrouppurchase;
    }

    /**
     * 是否是团购酒店
     * @param isgrouppurchase 是否是团购酒店
     */
    public void setIsgrouppurchase(Integer isgrouppurchase) {
        this.isgrouppurchase = isgrouppurchase;
    }

    /**
     * 城市名称 冗余字段
     * @return redundantCityName 城市名称 冗余字段
     */
    public String getRedundantcityname() {
        return redundantcityname;
    }

    /**
     * 城市名称 冗余字段
     * @param redundantcityname 城市名称 冗余字段
     */
    public void setRedundantcityname(String redundantcityname) {
        this.redundantcityname = redundantcityname == null ? null : redundantcityname.trim();
    }

    /**
     * 省名称 冗余字段
     * @return redundantProvinceName 省名称 冗余字段
     */
    public String getRedundantprovincename() {
        return redundantprovincename;
    }

    /**
     * 省名称 冗余字段
     * @param redundantprovincename 省名称 冗余字段
     */
    public void setRedundantprovincename(String redundantprovincename) {
        this.redundantprovincename = redundantprovincename == null ? null : redundantprovincename.trim();
    }

    /**
     * 国家名称 冗余字段
     * @return redundantCountryName 国家名称 冗余字段
     */
    public String getRedundantcountryname() {
        return redundantcountryname;
    }

    /**
     * 国家名称 冗余字段
     * @param redundantcountryname 国家名称 冗余字段
     */
    public void setRedundantcountryname(String redundantcountryname) {
        this.redundantcountryname = redundantcountryname == null ? null : redundantcountryname.trim();
    }

    /**
     * 酒店库存（冗余，每天开定时任务的时候更新）
     * @return redundantHotelStore 酒店库存（冗余，每天开定时任务的时候更新）
     */
    public Integer getRedundanthotelstore() {
        return redundanthotelstore;
    }

    /**
     * 酒店库存（冗余，每天开定时任务的时候更新）
     * @param redundanthotelstore 酒店库存（冗余，每天开定时任务的时候更新）
     */
    public void setRedundanthotelstore(Integer redundanthotelstore) {
        this.redundanthotelstore = redundanthotelstore;
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