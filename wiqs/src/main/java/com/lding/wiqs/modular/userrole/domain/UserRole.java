package com.lding.wiqs.modular.userrole.domain;

import javax.persistence.*;

@Table(name = "c_user_role")
public class UserRole {
    /**
     * 主键 UUID
     */
    @Id
    private String id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private String roleId;

    /**
     * 获取主键 UUID
     *
     * @return id - 主键 UUID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键 UUID
     *
     * @param id 主键 UUID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}