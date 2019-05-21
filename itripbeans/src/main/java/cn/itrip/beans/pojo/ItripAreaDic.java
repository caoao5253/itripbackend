package cn.itrip.beans.pojo;

import java.util.Date;

/**
 * 区域字典表
 */
public class ItripAreaDic {
    /**
     * 主键
     */
    private Long id;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 区域编号
     */
    private String areano;

    /**
     * 父级区域
     */
    private Long parent;

    /**
     * 0:未激活 1:已激活
     */
    private Integer isactivated;

    /**
     * 是否是商圈(0:不是 1:是)
     */
    private Integer istradingarea;

    /**
     * 是否是热门城市
     * (0:不是 1：是)
     */
    private Integer ishot;

    /**
     * 区域级别(0:国家级 1:省级 2:市级 3:县/区)
     */
    private Integer level;

    /**
     * 1:国内 2：国外
     */
    private Integer ischina;

    /**
     * 区域名称拼音
     */
    private String pinyin;

    /**
     * 创建时间
     */
    private Date creationdate;

    /**
     * 创建id
     */
    private Long createdby;

    /**
     * 更新时间
     */
    private Date modifydate;

    /**
     * 更新者id
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
     * 区域名称
     * @return name 区域名称
     */
    public String getName() {
        return name;
    }

    /**
     * 区域名称
     * @param name 区域名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 区域编号
     * @return areaNo 区域编号
     */
    public String getAreano() {
        return areano;
    }

    /**
     * 区域编号
     * @param areano 区域编号
     */
    public void setAreano(String areano) {
        this.areano = areano == null ? null : areano.trim();
    }

    /**
     * 父级区域
     * @return parent 父级区域
     */
    public Long getParent() {
        return parent;
    }

    /**
     * 父级区域
     * @param parent 父级区域
     */
    public void setParent(Long parent) {
        this.parent = parent;
    }

    /**
     * 0:未激活 1:已激活
     * @return isActivated 0:未激活 1:已激活
     */
    public Integer getIsactivated() {
        return isactivated;
    }

    /**
     * 0:未激活 1:已激活
     * @param isactivated 0:未激活 1:已激活
     */
    public void setIsactivated(Integer isactivated) {
        this.isactivated = isactivated;
    }

    /**
     * 是否是商圈(0:不是 1:是)
     * @return isTradingArea 是否是商圈(0:不是 1:是)
     */
    public Integer getIstradingarea() {
        return istradingarea;
    }

    /**
     * 是否是商圈(0:不是 1:是)
     * @param istradingarea 是否是商圈(0:不是 1:是)
     */
    public void setIstradingarea(Integer istradingarea) {
        this.istradingarea = istradingarea;
    }

    /**
     * (0:不是 1：是)
     * @return isHot (0:不是 1：是)
     */
    public Integer getIshot() {
        return ishot;
    }

    /**
     * (0:不是 1：是)
     * @param ishot (0:不是 1：是)
     */
    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    /**
     * 区域级别(0:国家级 1:省级 2:市级 3:县/区)
     * @return level 区域级别(0:国家级 1:省级 2:市级 3:县/区)
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 区域级别(0:国家级 1:省级 2:市级 3:县/区)
     * @param level 区域级别(0:国家级 1:省级 2:市级 3:县/区)
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 1:国内 2：国外
     * @return isChina 1:国内 2：国外
     */
    public Integer getIschina() {
        return ischina;
    }

    /**
     * 1:国内 2：国外
     * @param ischina 1:国内 2：国外
     */
    public void setIschina(Integer ischina) {
        this.ischina = ischina;
    }

    /**
     * 
     * @return pinyin 
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 
     * @param pinyin 
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
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