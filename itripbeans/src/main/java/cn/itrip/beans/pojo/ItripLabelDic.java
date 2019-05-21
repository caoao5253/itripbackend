package cn.itrip.beans.pojo;

import java.util.Date;

/**
 * 通用字典表
 */
public class ItripLabelDic {
    /**
     * 主键
     */
    private Long id;

    /**
     * key值 通用字典表名称
     */
    private String name;

    /**
     * value值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

    /**
     * 父级标签id(1级标签则为0)
     */
    private Long parentid;

    /**
     * 标签图片地址
     */
    private String pic;

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
     * key值
     * @return name key值
     */
    public String getName() {
        return name;
    }

    /**
     * key值
     * @param name key值
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * value值
     * @return value value值
     */
    public String getValue() {
        return value;
    }

    /**
     * value值
     * @param value value值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 描述
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 父级标签id(1级标签则为0)
     * @return parentId 父级标签id(1级标签则为0)
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * 父级标签id(1级标签则为0)
     * @param parentid 父级标签id(1级标签则为0)
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * 标签图片地址
     * @return pic 标签图片地址
     */
    public String getPic() {
        return pic;
    }

    /**
     * 标签图片地址
     * @param pic 标签图片地址
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
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