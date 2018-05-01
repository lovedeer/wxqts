package com.wxqts.platform.service;


import java.util.List;

import com.wxqts.platform.bean.SysRoleOrganization;
import com.wxqts.platform.dto.PageInfo;
import com.wxqts.platform.dto.SysRoleOrganizationTree;

/**
 * @Author 壹贰零柒
 * @Date 2016/10/17/16:30
 * @Description
 */
public interface SysRoleOrganizationService {
    boolean isExistName(String name, long parentId);

    long insertRoleOrganization(SysRoleOrganization roleOrganization);

    boolean isExistNameExcludeId(long id, String name, long parentId);

    void updateRoleOrganization(SysRoleOrganization roleOrganization);

    SysRoleOrganization selectRoleOrganizationById(long id);

    PageInfo selectPage(int page, int rows, long id);

    public SysRoleOrganizationTree selectSysRoleOrganizationTree(long id);

    public List<SysRoleOrganizationTree> selectSysRoleOrganizationTreeChildrenList(long id);
}
