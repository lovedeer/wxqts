package com.wxqts.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxqts.platform.bean.SysLoginStatus;

public interface SysLoginStatusMapper {
    //新增
    public Long insert(SysLoginStatus SysLoginStatus);

    //更新
    public void update(SysLoginStatus SysLoginStatus);

    //通过对象进行查询
    public SysLoginStatus select(SysLoginStatus SysLoginStatus);

    //通过id进行查询
    public SysLoginStatus selectById(@Param("id") Long id);

    //查询全部
    public List<SysLoginStatus> selectAll();

    //查询数量
    public int selectCounts();

    SysLoginStatus selectByUserIdAndPlatform(@Param("userId") Long userId, @Param("platform") int platform);

    List<SysLoginStatus> selectByUserId(@Param("userId") long userId);
}