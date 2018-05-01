package com.wxqts.platform.dto;

import java.io.Serializable;

import com.wxqts.platform.bean.SysOrganization;
import com.wxqts.platform.bean.SysRole;

/**
 * @Author 壹贰零柒
 * @Date 2017-12-07/13:57
 * @Description
 */
public class UserRoleOriganization implements Serializable {
    private SysRole role;
    private SysOrganization organization;

    public UserRoleOriganization(SysRole role, SysOrganization organization) {
        this.role = role;
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "UserRoleOriganization{" +
                "role=" + role +
                ", organization=" + organization +
                '}';
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public SysOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(SysOrganization organization) {
        this.organization = organization;
    }
}
