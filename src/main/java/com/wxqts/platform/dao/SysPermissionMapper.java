package com.wxqts.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxqts.platform.bean.SysPermission;

public interface SysPermissionMapper {
    //新增
    public Long insert(SysPermission SysPermission);

    //更新
    public void update(SysPermission SysPermission);

    //通过对象进行查询
    public SysPermission select(SysPermission SysPermission);

    //通过id进行查询
    public SysPermission selectById(@Param("id") Long id);

    //查询全部
    public List<SysPermission> selectAll();

    //查询数量
    public int selectCounts();

    boolean isExistName(@Param("groupId") long groupId, @Param("name") String name);

    boolean isExistCode(@Param("groupId") long groupId, @Param("code") String code);

    boolean isExistNameExcludeId(@Param("id") long id, @Param("groupId") long groupId, @Param("name") String name);

    boolean isExistCodeExcludeId(@Param("id") long id, @Param("groupId") long groupId, @Param("code") String code);
}