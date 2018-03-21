package com.lding.wiqs.modular.role.domain;

import javax.persistence.*;

@Table(name = "c_role")
public class Role {
    /**
     * 主键 UUID
     */
    @Id
    @Column(name = "role_id")
    private String roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 账号是否锁定，0：可用，1：锁定
     */
    @Column(name = "role_is_lock")
    private String roleIsLock;

    /**
     * 获取主键 UUID
     *
     * @return role_id - 主键 UUID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置主键 UUID
     *
     * @param roleId 主键 UUID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取账号是否锁定，0：可用，1：锁定
     *
     * @return role_is_lock - 账号是否锁定，0：可用，1：锁定
     */
    public String getRoleIsLock() {
        return roleIsLock;
    }

    /**
     * 设置账号是否锁定，0：可用，1：锁定
     *
     * @param roleIsLock 账号是否锁定，0：可用，1：锁定
     */
    public void setRoleIsLock(String roleIsLock) {
        this.roleIsLock = roleIsLock;
    }
}