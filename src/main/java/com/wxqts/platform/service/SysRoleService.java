package com.wxqts.platform.service;

import com.wxqts.platform.bean.SysRole;
import com.wxqts.platform.dto.PageInfo;

/**
 * @Author 壹贰零柒
 * @Date 2016/10/14/14:53
 * @Description
 */
public interface SysRoleService {
    boolean isExsitRoleName(String name);

    long insertRole(SysRole sysRole, String permissionIds);

    void updateRole(SysRole sysRole, String permissionIds);

    boolean isExsitRoleNameExcludeId(long id, String name);

    SysRole selectById(long id);

    PageInfo selectPage(int page, int row);

    void deleteRole(SysRole sysRole);
}
