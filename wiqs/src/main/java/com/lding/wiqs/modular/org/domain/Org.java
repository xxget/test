package com.lding.wiqs.modular.org.domain;

import javax.persistence.*;

@Table(name = "c_org")
public class Org {
    /**
     * 主键 UUID
     */
    @Id
    @Column(name = "org_id")
    private String orgId;

    /**
     * 机构编码
     */
    @Column(name = "org_no")
    private String orgNo;

    /**
     * 机构名称
     */
    @Column(name = "org_name")
    private String orgName;

    /**
     * 上级机构ID
     */
    @Column(name = "org_pid")
    private String orgPid;

    /**
     * 机构类型
     */
    @Column(name = "org_type")
    private String orgType;

    /**
     * 备注/联系方式
     */
    private String contant;

    /**
     * 获取主键 UUID
     *
     * @return org_id - 主键 UUID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置主键 UUID
     *
     * @param orgId 主键 UUID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取机构编码
     *
     * @return org_no - 机构编码
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * 设置机构编码
     *
     * @param orgNo 机构编码
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    /**
     * 获取机构名称
     *
     * @return org_name - 机构名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置机构名称
     *
     * @param orgName 机构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 获取上级机构ID
     *
     * @return org_pid - 上级机构ID
     */
    public String getOrgPid() {
        return orgPid;
    }

    /**
     * 设置上级机构ID
     *
     * @param orgPid 上级机构ID
     */
    public void setOrgPid(String orgPid) {
        this.orgPid = orgPid;
    }

    /**
     * 获取机构类型
     *
     * @return org_type - 机构类型
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 设置机构类型
     *
     * @param orgType 机构类型
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * 获取备注/联系方式
     *
     * @return contant - 备注/联系方式
     */
    public String getContant() {
        return contant;
    }

    /**
     * 设置备注/联系方式
     *
     * @param contant 备注/联系方式
     */
    public void setContant(String contant) {
        this.contant = contant;
    }
}