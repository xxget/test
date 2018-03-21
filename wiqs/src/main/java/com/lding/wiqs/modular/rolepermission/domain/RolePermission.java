package com.lding.wiqs.modular.rolepermission.domain;

import javax.persistence.*;

@Table(name = "c_role_permission")
public class RolePermission {
    /**
     * 主键 UUID
     */
    @Id
    private String id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private String roleId;

    /**
     * 权限id
     */
    @Column(name = "per_id")
    private String perId;

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
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取权限id
     *
     * @return per_id - 权限id
     */
    public String getPerId() {
        return perId;
    }

    /**
     * 设置权限id
     *
     * @param perId 权限id
     */
    public void setPerId(String perId) {
        this.perId = perId;
    }
}