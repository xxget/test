package com.lding.wiqs.modular.typedesc.domain;

import javax.persistence.*;

@Table(name = "p_type_desc")
public class TypeDesc {
    /**
     * 问题描述编号
     */
    @Id
    @Column(name = "pd_id")
    private String pdId;

    /**
     * 问题描述名称
     */
    @Column(name = "pd_name")
    private String pdName;

    /**
     * 上级编号
     */
    @Column(name = "pd_pid")
    private String pdPid;

    /**
     * 问题类型编号
     */
    @Column(name = "pd_type_id")
    private String pdTypeId;

    /**
     * 问题类型名称
     */
    @Column(name = "pd_type_name")
    private String pdTypeName;

    /**
     * 备注/联系方式
     */
    @Column(name = "pd_contant")
    private String pdContant;

    /**
     * 获取问题描述编号
     *
     * @return pd_id - 问题描述编号
     */
    public String getPdId() {
        return pdId;
    }

    /**
     * 设置问题描述编号
     *
     * @param pdId 问题描述编号
     */
    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    /**
     * 获取问题描述名称
     *
     * @return pd_name - 问题描述名称
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * 设置问题描述名称
     *
     * @param pdName 问题描述名称
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * 获取上级编号
     *
     * @return pd_pid - 上级编号
     */
    public String getPdPid() {
        return pdPid;
    }

    /**
     * 设置上级编号
     *
     * @param pdPid 上级编号
     */
    public void setPdPid(String pdPid) {
        this.pdPid = pdPid;
    }

    /**
     * 获取问题类型编号
     *
     * @return pd_type_id - 问题类型编号
     */
    public String getPdTypeId() {
        return pdTypeId;
    }

    /**
     * 设置问题类型编号
     *
     * @param pdTypeId 问题类型编号
     */
    public void setPdTypeId(String pdTypeId) {
        this.pdTypeId = pdTypeId;
    }

    /**
     * 获取问题类型名称
     *
     * @return pd_type_name - 问题类型名称
     */
    public String getPdTypeName() {
        return pdTypeName;
    }

    /**
     * 设置问题类型名称
     *
     * @param pdTypeName 问题类型名称
     */
    public void setPdTypeName(String pdTypeName) {
        this.pdTypeName = pdTypeName;
    }

    /**
     * 获取备注/联系方式
     *
     * @return pd_contant - 备注/联系方式
     */
    public String getPdContant() {
        return pdContant;
    }

    /**
     * 设置备注/联系方式
     *
     * @param pdContant 备注/联系方式
     */
    public void setPdContant(String pdContant) {
        this.pdContant = pdContant;
    }
}