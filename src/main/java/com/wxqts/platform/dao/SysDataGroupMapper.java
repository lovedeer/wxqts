package com.wxqts.platform.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxqts.platform.bean.SysDataGroup;

public interface SysDataGroupMapper {
    //新增
    public Long insert(SysDataGroup SysDataGroup);

    //更新
    public void update(SysDataGroup SysDataGroup);

    //通过对象进行查询
    public SysDataGroup select(SysDataGroup SysDataGroup);

    //通过id进行查询
    public SysDataGroup selectById(@Param("id") Long id);

    //查询全部
    public List<SysDataGroup> selectAll();

    //查询数量
    public int selectCounts();

    boolean isExistName(@Param("name") String name);
}