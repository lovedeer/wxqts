package com.wxqts.platform.service;

import java.io.Serializable;

import com.wxqts.platform.bean.SysUser;
import com.wxqts.platform.dto.LoginInfo;
import com.wxqts.platform.dto.PageInfo;

/**
 * @Author: 壹贰零柒
 * @Date : 2016/10/7
 */
public interface SysUserService {

    long insertUser(SysUser user, String jobIds, String permissionIds);

    boolean isExistLoginName(String loginName);

    void deleteById(long id);

    SysUser selectById(long id);

    boolean isExistLoginNameExcludeId(long id, String loginName);

    void updateUser(SysUser user, String jobIds, String permissionIds);

    PageInfo selectPage(int page, int rows, String sort, String order, String loginName, String zhName, String email, String phone, String address);

    void updateUser(SysUser user);

    SysUser selectByLoginName(String loginName);

    LoginInfo login(SysUser user, Serializable id, int platform);


}
