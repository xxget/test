package com.lding.wiqs.modular.permission.domain;

import javax.persistence.*;

@Table(name = "c_permission")
public class Permission {
    /**
     * 主键 UUID
     */
    @Id
    @Column(name = "per_id")
    private String perId;

    /**
     * 资源名称
     */
    @Column(name = "per_name")
    private String perName;

    /**
     * 资源类型：menu,button,
     */
    @Column(name = "per_type")
    private String perType;

    /**
     * 访问url地址
     */
    @Column(name = "per_url")
    private String perUrl;

    /**
     * 权限代码字符串
     */
    private String percode;

    /**
     * 父结点id(UUID)
     */
    private String parentid;

    /**
     * 父结点id列表串
     */
    private String parentids;

    /**
     * 排序号
     */
    private String sortstring;

    /**
     * 获取主键 UUID
     *
     * @return per_id - 主键 UUID
     */
    public String getPerId() {
        return perId;
    }

    /**
     * 设置主键 UUID
     *
     * @param perId 主键 UUID
     */
    public void setPerId(String perId) {
        this.perId = perId;
    }

    /**
     * 获取资源名称
     *
     * @return per_name - 资源名称
     */
    public String getPerName() {
        return perName;
    }

    /**
     * 设置资源名称
     *
     * @param perName 资源名称
     */
    public void setPerName(String perName) {
        this.perName = perName;
    }

    /**
     * 获取资源类型：menu,button,
     *
     * @return per_type - 资源类型：menu,button,
     */
    public String getPerType() {
        return perType;
    }

    /**
     * 设置资源类型：menu,button,
     *
     * @param perType 资源类型：menu,button,
     */
    public void setPerType(String perType) {
        this.perType = perType;
    }

    /**
     * 获取访问url地址
     *
     * @return per_url - 访问url地址
     */
    public String getPerUrl() {
        return perUrl;
    }

    /**
     * 设置访问url地址
     *
     * @param perUrl 访问url地址
     */
    public void setPerUrl(String perUrl) {
        this.perUrl = perUrl;
    }

    /**
     * 获取权限代码字符串
     *
     * @return percode - 权限代码字符串
     */
    public String getPercode() {
        return percode;
    }

    /**
     * 设置权限代码字符串
     *
     * @param percode 权限代码字符串
     */
    public void setPercode(String percode) {
        this.percode = percode;
    }

    /**
     * 获取父结点id(UUID)
     *
     * @return parentid - 父结点id(UUID)
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * 设置父结点id(UUID)
     *
     * @param parentid 父结点id(UUID)
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取父结点id列表串
     *
     * @return parentids - 父结点id列表串
     */
    public String getParentids() {
        return parentids;
    }

    /**
     * 设置父结点id列表串
     *
     * @param parentids 父结点id列表串
     */
    public void setParentids(String parentids) {
        this.parentids = parentids;
    }

    /**
     * 获取排序号
     *
     * @return sortstring - 排序号
     */
    public String getSortstring() {
        return sortstring;
    }

    /**
     * 设置排序号
     *
     * @param sortstring 排序号
     */
    public void setSortstring(String sortstring) {
        this.sortstring = sortstring;
    }
}