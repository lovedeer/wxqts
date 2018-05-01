package com.wxqts.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxqts.platform.bean.SysRoleOrganization;

public interface SysRoleOrganizationMapper {
    //新增
    public Long insert(SysRoleOrganization SysRoleOrganization);

    //更新
    public void update(SysRoleOrganization SysRoleOrganization);

    //通过对象进行查询
    public SysRoleOrganization select(SysRoleOrganization SysRoleOrganization);

    //通过id进行查询
    public SysRoleOrganization selectById(@Param("id") Long id);

    //查询全部
    public List<SysRoleOrganization> selectAll();

    //查询数量
    public int selectCounts();

    boolean isExistName(@Param("name") String name, @Param("parentId") long parentId);

    boolean isExistNameExcludeId(@Param("id") long id, @Param("name") String name, @Param("parentId") long parentId);

    List<SysRoleOrganization> selectChildren(@Param("parentId") long parentId);

    List<Long> selectByRoleId(@Param("roleId") long roleId);
}