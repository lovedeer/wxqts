package com.wxqts.platform.service;



import java.util.List;

import com.wxqts.platform.bean.SysOrganization;
import com.wxqts.platform.dto.PageInfo;
import com.wxqts.platform.dto.SysOrganizationTree;

/**
 * @Author: 壹贰零柒
 * @Date : 2016/10/10
 */
public interface SysOrganizationService {
    Long insertOrganization(SysOrganization sysOrganization);

    int deleteOrganization(long id);

    void updateOrganization(SysOrganization organization);

    PageInfo selectPage(int page, int row, long id);

    public SysOrganizationTree selectSysOrganizationTree(long id);

    public List<SysOrganizationTree> selectChildrenTreeList(long id);

    boolean isExistFullName(String fullName);

    SysOrganization selectOrganization(long id);

    boolean isExistFullNameExcludeId(long id, String fullName);

}
