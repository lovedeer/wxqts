package com.wxqts.platform.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxqts.platform.bean.SysRolePermission;

public interface SysRolePermissionMapper {
    //新增
    public Long insert(SysRolePermission SysRolePermission);

    //更新
    public void update(SysRolePermission SysRolePermission);

    //通过对象进行查询
    public SysRolePermission select(SysRolePermission SysRolePermission);

    //通过id进行查询
    public SysRolePermission selectById(@Param("id") Long id);

    //查询全部
    public List<SysRolePermission> selectAll();

    //查询数量
    public int selectCounts();

    void deleteByRoleId(@Param("roleId") Long roleId);

    List<SysRolePermission> selectByRoleId(@Param("roleId") Long roleId);
}