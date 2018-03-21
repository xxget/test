package com.lding.wiqs.modular.userinfo.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "c_user_info")
/**
 * <p>Title: UserInfo</p>  
 * <p>Description: 用户基本信息</p>  
 * @author xixuguang 
 * @date 2018年3月5日 下午2:32:47
 *
 */
public class UserInfo {
    /**
     * 主键
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 工号
     */
    @Column(name = "job_id")
    private String jobId;

    /**
     * 部门ID
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 条线编号
     */
    @Column(name = "line_id")
    private String lineId;

    /**
     * 组编号
     */
    @Column(name = "group_id")
    private String groupId;

    /**
     * 登陆名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码(密文)
     */
    private String password;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 联系方式
     */
    private String address;

    /**
     * 盐
     */
    private String salt;

    /**
     * 账号是否锁定，0：可用，1：锁定
     */
    private String locked;

    /**
     * 性别0未知；1：男；2:女。
     */
    private String sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * 状态
     */
    @Column(name = "is_state")
    private String isState;

    /**
     * 证件类型
     */
    @Column(name = "id_type")
    private String idType;

    /**
     * 证件号码
     */
    @Column(name = "id_num")
    private String idNum;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 头像
     */
    private String icon;

    /**
     * 获取主键
     *
     * @return user_id - 主键
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置主键
     *
     * @param userId 主键
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取工号
     *
     * @return job_id - 工号
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * 设置工号
     *
     * @param jobId 工号
     */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * 获取部门ID
     *
     * @return org_id - 部门ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置部门ID
     *
     * @param orgId 部门ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取条线编号
     *
     * @return line_id - 条线编号
     */
    public String getLineId() {
        return lineId;
    }

    /**
     * 设置条线编号
     *
     * @param lineId 条线编号
     */
    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    /**
     * 获取组编号
     *
     * @return group_id - 组编号
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置组编号
     *
     * @param groupId 组编号
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取登陆名
     *
     * @return user_name - 登陆名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置登陆名
     *
     * @param userName 登陆名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码(密文)
     *
     * @return password - 密码(密文)
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码(密文)
     *
     * @param password 密码(密文)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取联系方式
     *
     * @return address - 联系方式
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置联系方式
     *
     * @param address 联系方式
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取盐
     *
     * @return salt - 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐
     *
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取账号是否锁定，0：可用，1：锁定
     *
     * @return locked - 账号是否锁定，0：可用，1：锁定
     */
    public String getLocked() {
        return locked;
    }

    /**
     * 设置账号是否锁定，0：可用，1：锁定
     *
     * @param locked 账号是否锁定，0：可用，1：锁定
     */
    public void setLocked(String locked) {
        this.locked = locked;
    }

    /**
     * 获取性别0未知；1：男；2:女。
     *
     * @return sex - 性别0未知；1：男；2:女。
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别0未知；1：男；2:女。
     *
     * @param sex 性别0未知；1：男；2:女。
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取状态
     *
     * @return is_state - 状态
     */
    public String getIsState() {
        return isState;
    }

    /**
     * 设置状态
     *
     * @param isState 状态
     */
    public void setIsState(String isState) {
        this.isState = isState;
    }

    /**
     * 获取证件类型
     *
     * @return id_type - 证件类型
     */
    public String getIdType() {
        return idType;
    }

    /**
     * 设置证件类型
     *
     * @param idType 证件类型
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * 获取证件号码
     *
     * @return id_num - 证件号码
     */
    public String getIdNum() {
        return idNum;
    }

    /**
     * 设置证件号码
     *
     * @param idNum 证件号码
     */
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取头像
     *
     * @return icon - 头像
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置头像
     *
     * @param icon 头像
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}