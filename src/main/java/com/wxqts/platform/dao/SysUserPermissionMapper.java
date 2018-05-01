package com.wxqts.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxqts.platform.bean.SysUserPermission;

public interface SysUserPermissionMapper {
    //新增
    public Long insert(SysUserPermission SysUserPermission);

    //更新
    public void update(SysUserPermission SysUserPermission);

    //通过对象进行查询
    public SysUserPermission select(SysUserPermission SysUserPermission);

    //通过id进行查询
    public SysUserPermission selectById(@Param("id") Long id);

    //查询全部
    public List<SysUserPermission> selectAll();

    //查询数量
    public int selectCounts();

    void deleteByUserId(@Param("userId") long userId);

    List<SysUserPermission> selectByUserId(Long userId);
}