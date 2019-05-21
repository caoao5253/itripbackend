package cn.itrip.beans.pojo;

import java.util.Date;

public class ItripImage {
    /**
     * 主键
     */
    private Long id;

    /**
     * 图片类型(0:酒店图片1:房间图片2:评论图片)
     */
    private String type;

    /**
     * 关联id
     */
    private Long targetid;

    /**
     * 图片s上传顺序位置
     */
    private Integer position;

    /**
     * 图片地址
     */
    private String imgurl;

    /**
     * 创建时间
     */
    private Date creationdate;

    /**
     * 创建者
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
     * 图片类型(0:酒店图片1:房间图片2:评论图片)
     * @return type 图片类型(0:酒店图片1:房间图片2:评论图片)
     */
    public String getType() {
        return type;
    }

    /**
     * 图片类型(0:酒店图片1:房间图片2:评论图片)
     * @param type 图片类型(0:酒店图片1:房间图片2:评论图片)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 关联id
     * @return targetId 关联id
     */
    public Long getTargetid() {
        return targetid;
    }

    /**
     * 关联id
     * @param targetid 关联id
     */
    public void setTargetid(Long targetid) {
        this.targetid = targetid;
    }

    /**
     * 图片s上传顺序位置
     * @return position 图片s上传顺序位置
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * 图片s上传顺序位置
     * @param position 图片s上传顺序位置
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 图片地址
     * @return imgUrl 图片地址
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * 图片地址
     * @param imgurl 图片地址
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
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